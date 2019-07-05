package home.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.MemberDao;

@WebServlet(urlPatterns="/admin/delete.do")
public class DeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			�Է� : ���� ����� �̸���
			String email = req.getParameter("email");
			
//			ó�� : DAO
			MemberDao mdao = new MemberDao();
			mdao.delete(email);
			
//			��� : search.do
			resp.sendRedirect("search.do");
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}




