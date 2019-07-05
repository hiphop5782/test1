package home.servlet.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.CommentsDao;
import home.beans.CommentsDto;
@WebServlet(urlPatterns="/board/c_edit.do")
public class CommentsEditServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			CommentsDto cdto = new CommentsDto();
			cdto.setNo(Integer.parseInt(req.getParameter("no")));
			cdto.setContent(req.getParameter("content"));
			cdto.setOrigin(Integer.parseInt(req.getParameter("origin")));
			
			CommentsDao cdao = new CommentsDao();
			cdao.edit(cdto);
			
			resp.sendRedirect("content.do?no="+cdto.getOrigin());
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}
