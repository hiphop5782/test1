package home.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.MemberDao;
import home.beans.MemberDto;

//클라이언트의 아이디 중복확인 비동기 요청을 처리하는 서블릿
// - 들어오는 데이터 : 사용자의 이메일(email)
// - 내보낼 데이터 : 사용가능(Y), 사용중(N)
@WebServlet(urlPatterns="/member/id_check.do")
public class IdCheckServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력
			String email = req.getParameter("email");
			
//			처리
			MemberDao mdao = new MemberDao();
			MemberDto mdto = mdao.get(email);
			
//			출력
			resp.setContentType("text/plain");
			if(mdto == null)
				resp.getWriter().print("Y");
			else
				resp.getWriter().print("N");
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}








