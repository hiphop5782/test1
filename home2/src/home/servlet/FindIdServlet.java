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

@WebServlet(urlPatterns="/member/find_id.do")
public class FindIdServlet extends HttpServlet{
//	서블릿 실행 순서
//	[1] init : 서블릿이 최초 실행될 때 실행되는 메소드
//	[2] service, doGet, doPost, ... : 요청 발생시 실행하는 메소드
//	[3] destroy : 서블릿이 종료될 때 실행되는 메소드
	
//	GET : 아이디 찾기 입력 페이지로 포워드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher dispatcher = req.getRequestDispatcher("find_id.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/find_id.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력 : name, birth, phone ---> MemberDto
			req.setCharacterEncoding("UTF-8");
			MemberDto mdto = new MemberDto();
			mdto.setName(req.getParameter("name"));
			mdto.setBirth(req.getParameter("birth"));
			mdto.setPhone(req.getParameter("phone"));
			
//			처리 : MemberDao
			MemberDao mdao = new MemberDao();
			String email = mdao.findId(mdto);
			
//			null이든 아니든 첨부하여 출력페이지에 전달
			req.setAttribute("email", email);
			
//			RequestDispatcher dispatcher = req.getRequestDispatcher("find_id_result.jsp");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/find_id_result.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}






