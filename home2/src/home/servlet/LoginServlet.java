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
//		login.jsp�� ������
//		RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/login.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			�Է� : email, pw, remember
			req.setCharacterEncoding("UTF-8");
			MemberDto mdto = new MemberDto();
			mdto.setEmail(req.getParameter("email"));
			mdto.setPw(req.getParameter("pw"));
			String remember = req.getParameter("remember");
			
//			ó�� : MemberDao.login()
			MemberDao mdao = new MemberDao();
			boolean result = mdao.login(mdto);
			
//			��� : ������ ����, ���н� ����������
			if(result) {
//				��Ű�� �����ϰų� �����ϰų�(remember�� ������ ����)
				if(remember == null) {
					Cookie c = new Cookie("saveId", mdto.getEmail());
					c.setMaxAge(0);
					resp.addCookie(c);
				}
				else {
					Cookie c = new Cookie("saveId", mdto.getEmail());
					c.setMaxAge(1 * 7 * 24 * 60 * 60);//1��
					resp.addCookie(c);
				}
				
//				�������� req�� resp�ۿ� ����
//				�ʿ��� ������ �������� ��� req�� resp���� �����Ͽ� ���
//				���� = req.getSession()
//				���ø����̼� = req.getServletContext()
				req.getSession().setAttribute("ok", mdto.getEmail());
//				Email�� �̿��ؼ� ������ ȹ���ѵ� ���ǿ� �߰�
				MemberDto find = mdao.get(mdto.getEmail());
				req.getSession().setAttribute("auth", find.getAuth());
				
//				���� ���� �ð� ����
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





