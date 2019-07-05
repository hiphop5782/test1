package home.servlet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.MemberDao;
import home.beans.MemberDto;
@WebServlet(urlPatterns="/admin/info.do")
public class InfoServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
		 	MemberDao mdao = new MemberDao();
		 	MemberDto mdto = mdao.get(email);
		 	
		 	req.setAttribute("mdto", mdto);
		 	req.setAttribute("email", email);
		 	
		 	RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/info.jsp");
		 	dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
