package home.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.MemberDao;
import home.beans.MemberDto;
@WebServlet(urlPatterns="/member/info.do")
public class InfoServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = (String)req.getSession().getAttribute("ok");
		 	MemberDao mdao = new MemberDao();
		 	MemberDto mdto = mdao.get(email); 
		 	
//		 	데이터 첨부 및 포워딩
		 	req.setAttribute("mdto", mdto);
		 	
		 	RequestDispatcher dispatcher = 
		 						req.getRequestDispatcher("info.jsp");
		 	dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}






