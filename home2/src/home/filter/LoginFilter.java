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
//		이 필터는 로그인된 사용자만 통과할 수 있는 필터
//		[1] 로그인되어 있다면 통과
//		[2] 로그인이 되어있지 않다면 로그인 페이지로 리다이렉트
//		 - 로그인검사 : 세션에 ok란 이름의 값이 있냐 없냐로 검사
		HttpServletRequest request = (HttpServletRequest) req;
		String ok = (String)request.getSession().getAttribute("ok");
		if(ok == null) {	//로그아웃
			HttpServletResponse response = (HttpServletResponse) resp;
			response.sendRedirect(request.getContextPath()+"/member/login.do");
		}
		else {				//로그인
			chain.doFilter(req, resp);
		}
	}
}




