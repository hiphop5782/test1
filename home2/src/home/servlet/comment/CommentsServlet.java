package home.servlet.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.CommentsDao;
import home.beans.CommentsDto;
@WebServlet(urlPatterns="/board/comments.do")
public class CommentsServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			입력 : 파라미터 2개(content, origin) + 세션 1개(email)
			CommentsDto cdto = new CommentsDto();
			cdto.setContent(req.getParameter("content"));
			cdto.setOrigin(Integer.parseInt(req.getParameter("origin")));
			cdto.setWriter((String)req.getSession().getAttribute("ok"));
			
//			처리 : 등록
			CommentsDao cdao = new CommentsDao();
			cdao.insert(cdto);
			
//			출력 : content.do로 리다이렉트
			resp.sendRedirect("content.do?no="+cdto.getOrigin());
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}






