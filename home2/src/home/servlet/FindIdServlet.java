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
//	���� ���� ����
//	[1] init : ������ ���� ����� �� ����Ǵ� �޼ҵ�
//	[2] service, doGet, doPost, ... : ��û �߻��� �����ϴ� �޼ҵ�
//	[3] destroy : ������ ����� �� ����Ǵ� �޼ҵ�
	
//	GET : ���̵� ã�� �Է� �������� ������
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher dispatcher = req.getRequestDispatcher("find_id.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/find_id.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			�Է� : name, birth, phone ---> MemberDto
			req.setCharacterEncoding("UTF-8");
			MemberDto mdto = new MemberDto();
			mdto.setName(req.getParameter("name"));
			mdto.setBirth(req.getParameter("birth"));
			mdto.setPhone(req.getParameter("phone"));
			
//			ó�� : MemberDao
			MemberDao mdao = new MemberDao();
			String email = mdao.findId(mdto);
			
//			null�̵� �ƴϵ� ÷���Ͽ� ����������� ����
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






