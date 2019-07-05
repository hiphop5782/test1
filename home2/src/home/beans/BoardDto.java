package home.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDto {
	private int no;
	private String head;
	private String writer;
	private String title;
	private String content;
	private String when;
	
	private int parent;
	private int depth;
	private int team;
	
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}

	private int read;
	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", head=" + head + ", writer=" + writer + ", title=" + title + ", content="
				+ content + ", when=" + when + ", read=" + read + "]";
	}
	public BoardDto() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWhen() {
		return when;
	}
	
//	날짜만 반환하는 메소드	getDate()
//	시간만 반환하는 메소드	getTime()
	public String getDate() {
		return when.substring(0, 10);
	}
	public String getTime() {
		return when.substring(11, 16);
	}
//	오늘인지 아닌지 판정하여 자동 시간 반환
	public String getAuto() {
		Date d = new Date();
		Format f = new SimpleDateFormat("yyyy-MM-dd");
//		getDate() : 게시글 날짜, f.format(d) : 오늘 날짜
		if(getDate().equals(f.format(d))) 
			return this.getTime();
		else 
			return this.getDate();
	}
	
	public void setWhen(String when) {
		this.when = when;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	
	public void setData(ResultSet rs) throws SQLException{
		this.setNo(rs.getInt("no"));
		this.setHead(rs.getString("head"));
		this.setTitle(rs.getString("title"));
		this.setWriter(rs.getString("writer"));
		this.setContent(rs.getString("content"));
		this.setWhen(rs.getString("when"));
		this.setRead(rs.getInt("read"));
		//변경된 항목 추가
		this.setParent(rs.getInt("parent"));
		this.setDepth(rs.getInt("depth"));
		this.setTeam(rs.getInt("team"));
	}
}










