package home.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.MemberDao;
import home.beans.MemberDto;

//Ŭ���̾�Ʈ�� ���̵� �ߺ�Ȯ�� �񵿱� ��û�� ó���ϴ� ����
// - ������ ������ : ������� �̸���(email)
// - ������ ������ : ��밡��(Y), �����(N)
@WebServlet(urlPatterns="/member/id_check.do")
public class IdCheckServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			�Է�
			String email = req.getParameter("email");
			
//			ó��
			MemberDao mdao = new MemberDao();
			MemberDto mdto = mdao.get(email);
			
//			���
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








