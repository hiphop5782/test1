package home.servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.util.Paging;
@WebServlet(urlPatterns="/board/list.do")
public class ListServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//페이징 도구에게 요청정보를 전달하며 처리를 부탁
			Paging p = new Paging(req);
			p.calculate();
			
//			데이터 첨부
			req.setAttribute("p", p);
			
//			포워딩 코드
			RequestDispatcher dispatcher = 
										req.getRequestDispatcher("list.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}





