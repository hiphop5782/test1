package home.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentsDto {
	private int no;
	private String writer;
	private String content;
	private String when;
	private int origin;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public void setWhen(String when) {
		this.when = when;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public CommentsDto() {
		super();
	}
	public CommentsDto(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setWriter(rs.getString("writer"));
		setContent(rs.getString("content"));
		setWhen(rs.getString("when"));
		setOrigin(rs.getInt("origin"));
	}
	@Override
	public String toString() {
		return "ReplyDto [no=" + no + ", writer=" + writer + ", content=" + content + ", when=" + when + ", origin="
				+ origin + "]";
	}
}
