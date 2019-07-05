package home.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommentsDao {

	static DataSource src;
	static {
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

//	댓글 등록
	public void insert(CommentsDto cdto) throws Exception{
		Connection con = getConnection();
		
		String sql = "insert into comments values("
				+ "comments_seq.nextval, ?, ?, sysdate, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cdto.getWriter());
		ps.setString(2, cdto.getContent());
		ps.setInt(3, cdto.getOrigin());
		ps.execute();
		
		con.close();
	}
	
//	댓글 목록
	public List<CommentsDto> get(int origin) throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from comments "
								+ "where origin=? order by no asc";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, origin);
		ResultSet rs = ps.executeQuery();
		
		List<CommentsDto> list = new ArrayList<>();
		while(rs.next()) {
			CommentsDto cdto = new CommentsDto(rs);
			list.add(cdto);
		}
		
		con.close();
		
		return list;
	}

//	2번의 명령으로 삭제 후 원본글번호를 반환하도록 구현
//	[1] no에 해당하는 댓글의 원본글번호를 구해라
//	[2] no에 해당하는 댓글을 삭제해라
	public int delete(int no) throws Exception{
		Connection con = getConnection();
		
		String sql = "select origin from comments where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ResultSet rs = ps.executeQuery();
		rs.next();//100%
		int origin = rs.getInt("origin");
		ps.close();
		
		sql = "delete comments where no=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.execute();
		
		con.close();
		return origin;
	}

	public void edit(CommentsDto cdto) throws Exception{
		Connection con = getConnection();
		
		String sql = "update comments set content=? where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cdto.getContent());
		ps.setInt(2, cdto.getNo());
		ps.execute();
		
		con.close();
	}
	
}









