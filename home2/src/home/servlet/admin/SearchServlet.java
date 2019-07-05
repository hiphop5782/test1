package home.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.beans.MemberDao;
import home.beans.MemberDto;

@WebServlet(urlPatterns="/admin/search.do")
public class SearchServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		해야할일 : search.do 출력에 필요한 데이터를 구해서 전달
//		필요한 데이터 : type, keyword, list
		try {
//			필요한 처리
			String type = req.getParameter("type");
			String keyword = req.getParameter("keyword");
			
			MemberDao mdao = new MemberDao();
			//type 유무에 따라 검색을 진행
			List<MemberDto> list;
			if(type == null){
				list = null;
			}
			else{
				list = mdao.search(type, keyword); 
			}
			
//			데이터 첨부
			req.setAttribute("type", type);
			req.setAttribute("keyword", keyword);
			req.setAttribute("list", list);
			
//			포워딩
			RequestDispatcher dispatcher = 
							req.getRequestDispatcher("search.jsp");
			dispatcher.forward(req, resp);
		}
		catch(Exception e) {
			resp.sendError(500);
			e.printStackTrace();
		}
	}
}






