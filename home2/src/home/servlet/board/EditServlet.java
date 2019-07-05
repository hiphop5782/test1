package home.servlet.board;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.BoardDao;
import home.beans.BoardDto;

@WebServlet(urlPatterns="/board/edit.do")
public class EditServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(req.getParameter("no"));
			BoardDao bdao = new BoardDao();
			BoardDto bdto = bdao.get(no);  
			
			req.setAttribute("no", no);
			req.setAttribute("bdto", bdto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/board/edit.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력 : no, head, content, title
			BoardDto bdto = new BoardDto();
			bdto.setNo(Integer.parseInt(req.getParameter("no")));
			bdto.setHead(req.getParameter("head"));
			bdto.setTitle(req.getParameter("title"));
			bdto.setContent(req.getParameter("content"));
			
//			처리 :
			BoardDao bdao = new BoardDao();
			bdao.edit(bdto); 
			
//			출력 : content.do?no=xxx
			resp.sendRedirect("content.do?no="+bdto.getNo());
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}















