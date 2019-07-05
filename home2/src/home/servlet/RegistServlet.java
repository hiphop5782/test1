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
//		GET��� : ���� �������� ����
//		POST��� : ��� ó�� �� ��� �������� ����
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
//			�Է� : 7��
			req.setCharacterEncoding("UTF-8");//�ѱ� ó��
			MemberDto mdto = new MemberDto();
			mdto.setEmail(req.getParameter("email"));
			mdto.setPw(req.getParameter("pw"));
			mdto.setName(req.getParameter("name"));
			mdto.setBirth(req.getParameter("birth"));
			mdto.setPhone(req.getParameter("phone"));
			mdto.setQuestion(req.getParameter("question"));
			mdto.setAnswer(req.getParameter("answer"));
			
//			ó��
			MemberDao mdao = new MemberDao();
			mdao.regist(mdto);
			
//			��� : ���� ���� �ʰ� regist_result.jsp�� ����ڰ� �ٽ� ������ �Ѵ�
//						�̷��� �۾��� �����̷�Ʈ(Redirect)��� �θ���
//			resp.sendRedirect("������Ʈ �̸��� ������ ���");
//			�����
//			resp.sendRedirect("regist_result.jsp");
//			������
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





