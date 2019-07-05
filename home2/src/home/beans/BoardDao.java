package home.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDao {

//	과거의 연결 방식(직접 연결을 생성)
//	public Connection getConnection() throws Exception{
//		Class.forName("oracle.jdbc.OracleDriver");
//		return DriverManager.getConnection(
//				"jdbc:oracle:thin:@localhost:1521:xe", "home", "home");
//	}
	
//	개선된 연결 방식(DBCP방식, 미리 만들어 두고 렌탈하는 방식)
//	 - context.xml에 있는 Resource 정보를 알아야만 렌탈이 가능
//	 - static 키워드를 붙여서 모든 DAO 인스턴스에 공용으로 적용
//	static DataSource src = context.xml에 있는 resource 정보;
	static DataSource src;
	static {
//		src를 초기화하려면 코드가 복잡하므로 전용 초기화 구문을 사용
//		[1] 파일과 자원을 찾을 수 있는 탐사 객체 생성
//		[2] 탐사 객체를 이용하여 jdbc/oracle 이름을 가진 자원 정보를 src에 대입
		
		try {
			Context ctx = new InitialContext();
			src = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		}
		catch(Exception e) {
			System.err.println("DBCP 에러");
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() throws Exception{
		return src.getConnection();
	}
	
//	목록 메소드
	public List<BoardDto> list(int start, int end) throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from ("
								+ "select rownum rn, A.* from ("
									//계층형 SQL 명령
									+ "select * from board "
									+ "connect by prior no=parent "
									+ "start with parent is null "
									+ "order siblings by team desc, no asc"
								+ ") A"
							+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, start);
		ps.setInt(2, end);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> list = new ArrayList<>();
		
		while(rs.next()) {
			BoardDto bdto = new BoardDto();
			//현재 행의 데이터를 bdto에 복사
			bdto.setData(rs);
			list.add(bdto);
		}
		
		con.close();
		return list;
	}
	
//	항목 추출
	public BoardDto get(int no) throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from board where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ResultSet rs = ps.executeQuery();
		
		BoardDto bdto;
		if(rs.next()) {
			bdto = new BoardDto();
			bdto.setData(rs);
		}
		else {
			bdto = null;
		}
		
		con.close();
		return bdto;
	}

//	등록 : 정보 집어넣고 번호 반환
	public int write(BoardDto bdto) throws Exception{
		Connection con = getConnection();
		
//		[1] 현재 들어갈 게시글의 번호를 먼저 구한다
		String sql = "select board_seq.nextval from dual";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();//다음 데이터로 이동(무조건 1개)
		int no = rs.getInt("nextval");
//		int no = rs.getInt(1);
		ps.close();
		
//		[2] 답글일 경우는 부모글의 team 번호를 미리 구한다
		int team;
		if(bdto.getParent() == 0) {//새글
			team = no;
		}
		else {//답글
			sql = "select team from board where no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bdto.getParent());
			rs = ps.executeQuery();
			rs.next();
			team = rs.getInt("team");
			ps.close();
		}
		
		
//		[3] 1번에서 구한 번호를 이용하여 게시글을 추가한다
		sql = "insert into board values("
				+ "?, ?, ?, ?, ?, sysdate, 0, ?, "
				//nvl(항목, 값) : 항목이 null일 때 값으로 치환
				//nvl(depth, 0) : depth가 null이면 0으로 바꾸세요
				+ "(select nvl(depth, 0) +1 from board where no=?), ?"
			+ ")";
		ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.setString(2, bdto.getHead());
		ps.setString(3, bdto.getTitle());
		ps.setString(4, bdto.getWriter());
		ps.setString(5, bdto.getContent());
		if(bdto.getParent() == 0)
			ps.setNull(6, Types.INTEGER);//null을 추가하는 코드
		else
			ps.setInt(6, bdto.getParent());
		ps.setInt(7, bdto.getParent());
		ps.setInt(8, team);//2번에서 계산한 team 값
		ps.execute();
		
		con.close();
		return no;
	}
	
//	조회수 증가 메소드
	public void read(int no) throws Exception{
		Connection con = getConnection();
		
		String sql = "update board set read = read + 1 where no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.execute();
		
		con.close();
	}

//	글 삭제
	public void delete(int no) throws Exception{
		Connection con = getConnection();
		
		String sql = "delete board where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.execute();
		
		con.close();
	}

//	글 수정
	public void edit(BoardDto bdto) throws Exception{
		Connection con = getConnection();
		
		String sql = "update board "
									+ "set head=?, title=?, content=? "
									+ "where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, bdto.getHead());
		ps.setString(2, bdto.getTitle());
		ps.setString(3, bdto.getContent());
		ps.setInt(4, bdto.getNo());
		ps.execute();
		
		con.close();
	}
	
//	검색 메소드
//	type=writer 이거나 type=title+content 이거나 둘 중 하나
//	type=title+content라면 +를 ||로 바꿔서 처리
//	select * from board where writer like ? order by when desc
//	select * from board where title like ? or content like ? order by..
//	select * from board where title || content like ? order by ...
	
	public List<BoardDto> search(
			String type, String keyword, int start, int end) throws Exception{
		type = type.replace("+", "||");
		Connection con = getConnection();
		
		String sql = 
		"select * from ("
			+ "select rownum rn, A.* from ("
				+ "select * from board where "+type+" like '%'||?||'%' "
				+ "order by when desc"
			+ ") A"
		+ ") where rn between ? and ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyword);
		ps.setInt(2, start);
		ps.setInt(3, end);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> list = new ArrayList<>();
		
		while(rs.next()) {
			BoardDto bdto = new BoardDto();
			bdto.setData(rs);
			list.add(bdto);
		}
		
		con.close();
		return list;
	}
	
//	게시글 개수를 구하는 메소드(페이징용)
	public int getCount(String type, String keyword) throws Exception{
		boolean search = type != null && keyword != null;
		if(search) type = type.replace("+", "||");
		
		Connection con = getConnection();
		
		
		String sql = "select count(*) from board";
		if(search)		sql += " where "+type+" like '%'||?||'%'";
		PreparedStatement ps = con.prepareStatement(sql);
		if(search)		ps.setString(1, keyword);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		con.close();
		return count;
	}
}









