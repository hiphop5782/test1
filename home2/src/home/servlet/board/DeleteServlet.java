package home.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.BoardDao;
@WebServlet(urlPatterns="/board/delete.do")
public class DeleteServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력 : no
			int no = Integer.parseInt(req.getParameter("no"));
			
//			처리 : 
			BoardDao bdao = new BoardDao();
			bdao.delete(no);
			
//			출력 : list.do
			resp.sendRedirect("list.do");
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}







