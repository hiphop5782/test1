package home.servlet.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.CommentsDao;
import home.beans.CommentsDto;
@WebServlet(urlPatterns="/board/comments.do")
public class CommentsServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			�Է� : �Ķ���� 2��(content, origin) + ���� 1��(email)
			CommentsDto cdto = new CommentsDto();
			cdto.setContent(req.getParameter("content"));
			cdto.setOrigin(Integer.parseInt(req.getParameter("origin")));
			cdto.setWriter((String)req.getSession().getAttribute("ok"));
			
//			ó�� : ���
			CommentsDao cdao = new CommentsDao();
			cdao.insert(cdto);
			
//			��� : content.do�� �����̷�Ʈ
			resp.sendRedirect("content.do?no="+cdto.getOrigin());
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}






