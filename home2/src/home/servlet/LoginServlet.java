package home.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.MemberDao;
import home.beans.MemberDto;

@WebServlet(urlPatterns="/member/login.do")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		login.jsp로 포워드
//		RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/login.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력 : email, pw, remember
			req.setCharacterEncoding("UTF-8");
			MemberDto mdto = new MemberDto();
			mdto.setEmail(req.getParameter("email"));
			mdto.setPw(req.getParameter("pw"));
			String remember = req.getParameter("remember");
			
//			처리 : MemberDao.login()
			MemberDao mdao = new MemberDao();
			boolean result = mdao.login(mdto);
			
//			출력 : 성공시 메인, 실패시 오류페이지
			if(result) {
//				쿠키를 생성하거나 삭제하거나(remember의 유무에 따라서)
				if(remember == null) {
					Cookie c = new Cookie("saveId", mdto.getEmail());
					c.setMaxAge(0);
					resp.addCookie(c);
				}
				else {
					Cookie c = new Cookie("saveId", mdto.getEmail());
					c.setMaxAge(1 * 7 * 24 * 60 * 60);//1주
					resp.addCookie(c);
				}
				
//				서블릿에는 req와 resp밖에 없음
//				필요한 나머지 정보들은 모두 req와 resp에서 추출하여 사용
//				세션 = req.getSession()
//				어플리케이션 = req.getServletContext()
				req.getSession().setAttribute("ok", mdto.getEmail());
//				Email을 이용해서 권한을 획득한뒤 세션에 추가
				MemberDto find = mdao.get(mdto.getEmail());
				req.getSession().setAttribute("auth", find.getAuth());
				
//				최종 접속 시간 갱신
				mdao.recent(mdto.getEmail());
				
//				resp.sendRedirect("../");
				resp.sendRedirect(req.getContextPath());
//				RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
//				dispatcher.forward(req, resp);
			}
			else {
//				resp.sendRedirect("login_fail.jsp");
//				resp.sendRedirect(req.getContextPath()+"/member/login_fail.jsp");
//				RequestDispatcher dispatcher = req.getRequestDispatcher("login_fail.jsp");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/member/login_fail.jsp");
				dispatcher.forward(req, resp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}





