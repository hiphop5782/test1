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
@WebServlet(urlPatterns="/member/change_info.do")
public class ChangeInfoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = (String)req.getSession().getAttribute("ok");
			MemberDao mdao = new MemberDao();
			MemberDto mdto = mdao.get(email);
			
			req.setAttribute("mdto", mdto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/change_info.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력 : email, phone, question, answer
			req.setCharacterEncoding("UTF-8");
			MemberDto mdto = new MemberDto();
			mdto.setEmail(req.getParameter("email"));
			mdto.setPhone(req.getParameter("phone"));
			mdto.setQuestion(req.getParameter("question"));
			mdto.setAnswer(req.getParameter("answer"));
			
//			처리
			MemberDao mdao = new MemberDao();
			mdao.updateInfo(mdto);
			
//			출력
			resp.sendRedirect("info.do");
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}





