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

@WebServlet(urlPatterns="/member/check.do")
public class CheckServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/check.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력 : 이메일(세션), 비밀번호(파라미터), 목적지(파라미터)
			String email = (String)req.getSession().getAttribute("ok");
			String pw = req.getParameter("pw");
			String go = req.getParameter("go");
			
			MemberDto mdto = new MemberDto();
			mdto.setEmail(email);
			mdto.setPw(pw);
			
//			처리
			MemberDao mdao = new MemberDao();
			boolean result = mdao.login(mdto);
			
//			출력 : 그때그때 다름
			if(result) {//로그인 성공 : 목적지로 전송(통과)
				resp.sendRedirect(go);
			}
			else {
				resp.sendRedirect("check.do?error&go="+go);
			}
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}





