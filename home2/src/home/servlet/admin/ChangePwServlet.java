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
import home.util.StringGenerator;

@WebServlet(urlPatterns="/admin/change_pw.do")
public class ChangePwServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력
			String email = req.getParameter("email");
			
			MemberDto mdto = new MemberDto();
			mdto.setEmail(email);
			mdto.setPw(StringGenerator.random(10));
			
//			처리
			MemberDao mdao = new MemberDao();
			mdao.changePw(mdto);
			
//			출력
			req.setAttribute("pw", mdto.getPw());
			RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/change_pw.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}




