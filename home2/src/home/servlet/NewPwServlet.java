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
@WebServlet(urlPatterns="/member/new_pw.do")
public class NewPwServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/new_pw.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력
			MemberDto mdto = new MemberDto();
			mdto.setEmail(req.getParameter("email"));
			mdto.setPw(req.getParameter("pw"));
			
//			처리
			MemberDao mdao = new MemberDao();
			mdao.changePw(mdto);
			
//			출력
//			resp.sendRedirect("new_pw_result.jsp");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/new_pw_result.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}




