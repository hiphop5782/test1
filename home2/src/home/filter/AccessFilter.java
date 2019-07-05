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
import javax.servlet.http.HttpSession;

import home.beans.BoardDao;
import home.beans.BoardDto;

//@WebFilter(filterName="order4", 
//	urlPatterns= {
//		"/board/edit.do",
//		"/board/edit.do",
//		"/board/delete.do"
//})
public class AccessFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
//		게시글 작성자가 로그인한 사용자인지 검사하여
//		맞으면 ---> 통과
//		아니면 ---> /home/error/access.jsp 로 리다이렉트
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpSession session = request.getSession();
		HttpServletResponse response = (HttpServletResponse) arg1;
		try {
			
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao bdao = new BoardDao();
			BoardDto bdto = bdao.get(no);  
			
			//코드추가 : 현재 글의 작성자가 본인인지 확인하는 코드
			String email = (String)session.getAttribute("ok");
			boolean my = bdto.getWriter().equals(email);
			
			if(my) {
				arg2.doFilter(arg0, arg1);
			}
			else {
//				response.sendRedirect(request.getContextPath()+"/error/access.jsp");
				response.sendError(403);
			}
		}
		catch(Exception e) {
			response.sendError(500);
			e.printStackTrace();
		}
	}

}







