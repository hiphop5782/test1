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

@WebServlet(urlPatterns="/member/find_pw.do")
public class FindPwServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/find_pw.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			�Է� : email, question, answer ---> MemberDto
			req.setCharacterEncoding("UTF-8");
			MemberDto mdto = new MemberDto();
			mdto.setEmail(req.getParameter("email"));
			mdto.setQuestion(req.getParameter("question"));
			mdto.setAnswer(req.getParameter("answer"));
			
//			ó�� : MemberDao
			MemberDao mdao = new MemberDao();
			boolean exist = mdao.findPw(mdto);		
			
//			��� : ���������� �Ǵ� ��й�ȣ ���� ������
			if(exist)
				resp.sendRedirect("new_pw.do?email="+mdto.getEmail());
			else
				resp.sendRedirect("find_pw.do?error");
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}







