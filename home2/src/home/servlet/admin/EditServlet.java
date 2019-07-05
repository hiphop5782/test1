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
@WebServlet(urlPatterns="/admin/edit.do")
public class EditServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			String email = req.getParameter("email");
		 	MemberDao mdao = new MemberDao();
		 	MemberDto mdto = mdao.get(email); 
			
			req.setAttribute("mdto", mdto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/edit.jsp");
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
//			입력 : 7개(email, name, birth, phone, question, answer, auth)
			MemberDto mdto = new MemberDto();
			mdto.setEmail(req.getParameter("email"));
			mdto.setName(req.getParameter("name"));
			mdto.setBirth(req.getParameter("birth"));
			mdto.setPhone(req.getParameter("phone"));
			mdto.setQuestion(req.getParameter("question"));
			mdto.setAnswer(req.getParameter("answer"));
			mdto.setAuth(req.getParameter("auth"));
			
//			처리
			MemberDao mdao = new MemberDao();
			mdao.edit(mdto);
			
//			출력
			resp.sendRedirect("info.do?email="+mdto.getEmail());			
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}






