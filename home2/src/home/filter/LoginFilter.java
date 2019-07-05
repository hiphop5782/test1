package home.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(urlPatterns= {
//		"/member/logout.jsp",
//		"/member/info.do",
//		"/member/check.do",
//		"/member/check.do",
//		"/member/change_info.do",
//		"/member/change_info.do",
//		"/member/delete.do",
//		"/member/goodbye.jsp",
//		"/admin/*",
//		"/board/*"
//}, filterName="order2")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
//		�� ���ʹ� �α��ε� ����ڸ� ����� �� �ִ� ����
//		[1] �α��εǾ� �ִٸ� ���
//		[2] �α����� �Ǿ����� �ʴٸ� �α��� �������� �����̷�Ʈ
//		 - �α��ΰ˻� : ���ǿ� ok�� �̸��� ���� �ֳ� ���ķ� �˻�
		HttpServletRequest request = (HttpServletRequest) req;
		String ok = (String)request.getSession().getAttribute("ok");
		if(ok == null) {	//�α׾ƿ�
			HttpServletResponse response = (HttpServletResponse) resp;
			response.sendRedirect(request.getContextPath()+"/member/login.do");
		}
		else {				//�α���
			chain.doFilter(req, resp);
		}
	}
}




