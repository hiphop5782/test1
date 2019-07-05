package home.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.MemberDao;
@WebServlet(urlPatterns="/member/delete.do")
public class DeleteServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			�Է�
			String email = (String) req.getSession().getAttribute("ok");
			
//			ó��
			MemberDao mdao = new MemberDao();
			mdao.delete(email);
			
			req.getSession().removeAttribute("ok");
			
//			���
			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/goodbye.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}






