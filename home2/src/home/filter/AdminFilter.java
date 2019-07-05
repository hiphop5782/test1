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
//		관리자인지 검사하려면..
//		[1] 세션에 있는 auth 값이 필요
//		[2] 이 값은 로그인하면 생깁니다
//		[3] 값이 관리자여야만 통과, 아니면 403 에러 발송
		HttpServletRequest req = (HttpServletRequest) request;
		String auth = (String)req.getSession().getAttribute("auth");
		if(auth != null && auth.equals("관리자")) {
			chain.doFilter(request, response);
		}
		else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(403);
		}
	}
	
}



