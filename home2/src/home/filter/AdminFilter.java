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

//@WebFilter(urlPatterns="/admin/*", filterName="order3")
public class AdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		���������� �˻��Ϸ���..
//		[1] ���ǿ� �ִ� auth ���� �ʿ�
//		[2] �� ���� �α����ϸ� ����ϴ�
//		[3] ���� �����ڿ��߸� ���, �ƴϸ� 403 ���� �߼�
		HttpServletRequest req = (HttpServletRequest) request;
		String auth = (String)req.getSession().getAttribute("auth");
		if(auth != null && auth.equals("������")) {
			chain.doFilter(request, response);
		}
		else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(403);
		}
	}
	
}



