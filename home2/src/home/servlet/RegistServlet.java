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

//		/home/member/regist.do
//		GET방식 : 가입 페이지로 연결
//		POST방식 : 등록 처리 후 결과 페이지로 연동
@WebServlet(urlPatterns="/member/regist.do")
public class RegistServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = 
							req.getRequestDispatcher("regist.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력 : 7개
			req.setCharacterEncoding("UTF-8");//한글 처리
			MemberDto mdto = new MemberDto();
			mdto.setEmail(req.getParameter("email"));
			mdto.setPw(req.getParameter("pw"));
			mdto.setName(req.getParameter("name"));
			mdto.setBirth(req.getParameter("birth"));
			mdto.setPhone(req.getParameter("phone"));
			mdto.setQuestion(req.getParameter("question"));
			mdto.setAnswer(req.getParameter("answer"));
			
//			처리
			MemberDao mdao = new MemberDao();
			mdao.regist(mdto);
			
//			출력 : 직접 하지 않고 regist_result.jsp로 사용자가 다시 들어오게 한다
//						이러한 작업을 리다이렉트(Redirect)라고 부른다
//			resp.sendRedirect("프로젝트 이름을 포함한 경로");
//			상대경로
//			resp.sendRedirect("regist_result.jsp");
//			절대경로
//			resp.sendRedirect(req.getContextPath()+"/member/regist_result.jsp");
			
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("regist_result.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}





