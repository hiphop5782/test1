package home.util;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import home.beans.BoardDao;
import home.beans.BoardDto;

/**
 * 이 클래스는 페이징 처리를 수행하는 클래스입니다.
 * 요청정보(검색분류, 검색어, 페이지번호, 페이지크기, 블록크기)를 전달받아
 * 다음 정보를 계산합니다
 * [1] 시작/종료 블록번호
 * [2] 시작/종료 게시글순서
 * [3] 페이지 이동시 필요한 파라미터 문자열
 * [4] 목록/검색 별 게시글 개수
 */
public class Paging {
	private String type;
	private String keyword;
	private String pstr;
	private int psize = 10;//페이지 표시글 개수
	private int bsize = 10;//페이지 표시번호 개수
	
	//생성자
	public Paging(HttpServletRequest request) {
		this.type = request.getParameter("type");
		this.keyword = request.getParameter("keyword");
		this.pstr = request.getParameter("pno");
	}
	
	private boolean search;//검색여부
	private int pno;//현재페이지
	
	private int start, end;//시작글순서, 종료글순서
	private int count, pageCount;//전체게시글 수, 전체페이지 수
	private int startBlock, endBlock;//시작블록번호, 종료블록번호
	
	private List<BoardDto> list;//데이터가 저장된 목록
	private String query;//검색 문자열
	
	//계산 메소드
	public void calculate() throws Exception{
		//type, keyword 모두 null이 아니면 검색, 아니면 목록
		this.search = type != null && keyword != null; 
		
		//페이지 번호에 따라 start, end만 계산하면 페이징 구현이 가능
		try{
			pno = Integer.parseInt(pstr);
			if(pno <= 0) throw new Exception();
		}catch(Exception e){
			pno = 1;
		}
		
		this.end = pno * psize;
		this.start = end - (psize-1);
		
		//하단 네비게이션 부분을 계산(총 게시글 수를 이용한 페이지 수 계산)
		//공식 : 페이지수 = (게시글수 + 페이지크기 - 1) / 페이지크기
		BoardDao bdao = new BoardDao();
		this.count = bdao.getCount(type, keyword); 
		this.pageCount = (count + psize - 1) / psize;
		
		this.startBlock = (pno - 1) / bsize * bsize + 1;
		this.endBlock = startBlock + (bsize - 1);
		//번호 넘어감 방지 보정 처리
		if(endBlock > pageCount){
			endBlock = pageCount;
		}
		
		if(search){
			list = bdao.search(type, keyword, start, end); 
		}
		else{
			list = bdao.list(start, end);		
		}
		
		//검색어 유지를 위해 검색일 때와 아닐 때의 첨부되는 파라미터 처리
		//목록 : 검색어 처리 x
		//검색 : &type=ooo&keyword=ooo 형태의 파라미터를 생성
		if(search) {
			String tmp = URLEncoder.encode(type, "UTF-8");
			this.query = "&type="+tmp+"&keyword="+keyword;
		}else {
			this.query="";
		}
	}

	//getter 메소드를 원하는 만큼 구현
	public List<BoardDto> getList() {
		return list;
	}
	//첫블록인지 확인할 수 있는 getter
	public boolean isFirstBlock() {
		return this.startBlock == 1;
	}
	//첫페이지인지 확인할 수 있는 getter
	public boolean isFirstPage() {
		return this.pno == 1;
	}
	//마지막블록인지 확인할 수 있는 getter
	public boolean isLastBlock() {
		return this.endBlock == this.pageCount;
	}
	//마지막페이지인지 확인할 수 있는 getter
	public boolean isLastPage() {
		return this.pno == this.pageCount;
	}
	//다음 블록으로 이동하기 위한 파라미터 getter
	public String getNextBlock() {
		return "pno="+(endBlock+1)+query;
	}
	//다음 페이지로 이동하기 위한 파라미터 getter
	public String getNextPage() {
		return "pno="+(pno+1)+query;
	}
	//이전 페이지로 이동하기 위한 파라미터 getter
	public String getPrevPage() {
		return "pno="+(pno-1)+query;
	}
	//이전 블록으로 이동하기 위한 파라미터 getter
	public String getPrevBlock() {
		return "pno="+(startBlock-1)+query;
	}
	
	//i페이지로 이동하기 위한 파라미터 getter
	public String getPage(int i) {
		return "pno="+i+query;
	}
	
	//현재 페이지 반환하는 메소드
	public boolean isCurrentPage(int i) {
		return pno == i;
	}

	public String getType() {
		return type;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getPstr() {
		return pstr;
	}

	public int getPsize() {
		return psize;
	}

	public int getBsize() {
		return bsize;
	}

	public boolean isSearch() {
		return search;
	}

	public int getPno() {
		return pno;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getCount() {
		return count;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public int getEndBlock() {
		return endBlock;
	}

	public String getQuery() {
		return query;
	}

	@Override
	public String toString() {
		return "Paging [type=" + type + ", keyword=" + keyword + ", pstr=" + pstr + ", psize=" + psize + ", bsize="
				+ bsize + ", search=" + search + ", pno=" + pno + ", start=" + start + ", end=" + end + ", count="
				+ count + ", pageCount=" + pageCount + ", startBlock=" + startBlock + ", endBlock=" + endBlock + "]";
	}
	
}








