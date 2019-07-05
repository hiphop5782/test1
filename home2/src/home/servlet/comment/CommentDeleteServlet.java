package home.servlet.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.CommentsDao;

@WebServlet(urlPatterns="/board/c_delete.do")
public class CommentDeleteServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			�Է� : no(��۹�ȣ)
			int no = Integer.parseInt(req.getParameter("no"));
			
//			ó�� : ���� ---> �Ҽӱ۹�ȣ
			CommentsDao cdao = new CommentsDao();
			int origin = cdao.delete(no);
			
//			��� : content.do?no=�Ҽӱ۹�ȣ
			resp.sendRedirect("content.do?no="+origin);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}




