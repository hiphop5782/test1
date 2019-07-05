package home.servlet.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.BoardDao;
import home.beans.BoardDto;
import home.beans.CommentsDao;
import home.beans.CommentsDto;
import home.util.Paging;

@WebServlet(urlPatterns="/board/content.do")
public class ContentServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			계산
//			이 페이지는 글을 보여주기 위해 반드시 번호(no)가 필요합니다.
//			파라미터에서 넘어오는 번호를 받아서 불러오도록 처리
//			불러오기전 조회수 증가 처리
			int no = Integer.parseInt(req.getParameter("no"));
			BoardDao bdao = new BoardDao();
			
//			if(내가쓴글이 아니면서 처음읽을 때){
			bdao.read(no);//조회수 증가
//			}
			
			BoardDto bdto = bdao.get(no);  
			
//			코드추가 : 현재 글의 작성자가 본인인지 확인하는 코드
			String email = (String)req.getSession().getAttribute("ok");
			boolean my = bdto.getWriter().equals(email);
			
//			이 글의 댓글 목록을 불러오는 코드
			CommentsDao cdao = new CommentsDao();
			List<CommentsDto> list = cdao.get(no); 
			
//			List를 불러오기 위한 계산
			Paging p = new Paging(req);
			p.calculate();
			
//			데이터 첨부
			req.setAttribute("p", p);
			req.setAttribute("bdto", bdto);
			req.setAttribute("my", my);
			req.setAttribute("list", list);
			
//			포워딩
			RequestDispatcher dispatcher = 
							req.getRequestDispatcher("content.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}




