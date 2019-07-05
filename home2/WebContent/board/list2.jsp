<%@page import="java.net.URLEncoder"%>
<%@page import="home.beans.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="home.beans.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//자바 코드를 통해 데이터베이스의 목록을 불러오는 명령을 작성
	//(이 코드는 사용자에게 보여지지 않습니다)
	String type = request.getParameter("type");
	String keyword = request.getParameter("keyword");
	//type, keyword 모두 null이 아니면 검색, 아니면 목록
	boolean search = type != null && keyword != null; 
	
	//페이지 번호에 따라 start, end만 계산하면 페이징 구현이 가능
	int pno;
	try{
		pno = Integer.parseInt(request.getParameter("pno"));
		if(pno <= 0) throw new Exception();
	}catch(Exception e){
		pno = 1;
	}
	int psize = 10;//1p에 표시할 글 개수
	
	int end = pno * psize;
	int start = end - (psize-1);
	
	//하단 네비게이션 부분을 계산(총 게시글 수를 이용한 페이지 수 계산)
	//공식 : 페이지수 = (게시글수 + 페이지크기 - 1) / 페이지크기
	BoardDao bdao = new BoardDao();
	int count = bdao.getCount(type, keyword); 
	int pageCount = (count + psize - 1) / psize;
	
	int bsize = 10;//1p에 표시할 링크 개수
	int startBlock = (pno - 1) / bsize * bsize + 1;
	int endBlock = startBlock + (bsize - 1);
	//번호 넘어감 방지 보정 처리
	if(endBlock > pageCount){
		endBlock = pageCount;
	}
	
	List<BoardDto> list;
	if(search){
		list = bdao.search(type, keyword, start, end); 
	}
	else{
		list = bdao.list(start, end);		
	}
	
	//검색어 유지를 위해 검색일 때와 아닐 때의 첨부되는 파라미터 처리
	//목록 : 검색어 처리 x
	//검색 : &type=ooo&keyword=ooo 형태의 파라미터를 생성
	String query;
	if(search) {
		String tmp = URLEncoder.encode(type, "UTF-8");
		query = "&type="+tmp+"&keyword="+keyword;
	}else {
		query="";
	}
%>    

<jsp:include page="/template/header.jsp"></jsp:include>

<h4>type = <%=type%>, keyword = <%=keyword%></h4>
<h4>query = <%=query%></h4>
<h4>pno = <%=pno%>, psize = <%=psize%>, start = <%=start%>, end = <%=end%></h4>

<h1>자유 게시판</h1>
<h5>글은 자신의 인격입니다</h5>

<!-- 표 -->
<table border="1" width="800">
	<!-- 제목 -->
	<thead>
		<tr>
			<th>번호</th>
			<th width="50%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<!-- 게시글 -->
	<tbody align="center">
		<%for(BoardDto bdto : list){ %>
		<tr>
			<td><%=bdto.getNo()%></td>
			<td align="left">
			
				<%-- 말머리는 있을 때에만 [] 를 붙여서 출력한다. --%>
				<%if(!bdto.getHead().isEmpty()){ %>
				[<%=bdto.getHead()%>] 
				<%} %>
				
				<%-- content.do로 가기 위해 no를 첨부한다 --%>
				<a href="content.do?no=<%=bdto.getNo()%>">
					<%=bdto.getTitle()%>
				</a>
				
			</td>
			<td><%=bdto.getWriter()%></td>
			<td><%=bdto.getAuto()%></td>
			<td><%=bdto.getRead()%></td>
		</tr>
		<%} %>
	</tbody>
	<!-- 글쓰기 버튼 -->
	<tfoot>
		<tr>
			<td colspan="5" align="right">
				<a href="write.do">글쓰기</a>
			</td>
		</tr>
	</tfoot>
</table>

<!-- 네비게이션 + 검색창 -->
<br>
<form action="list.do" method="get">
<select name="type">
	<option value="title+content">제목+내용</option>
	<option value="writer">작성자</option>
</select>

<input type="search" name="keyword" placeholder="검색어" required>

<input type="submit" value="검색">
</form>

<h4>
	count = <%=count%>, pageCount = <%=pageCount%>,
	startBlock = <%=startBlock%>, endBlock = <%=endBlock%>
</h4>

<h4>
	<%if(startBlock > 1){ %>
		<%-- 이전 구간 링크 --%>
		<a href="list.do?pno=<%=startBlock-1%><%=query%>">&lt;&lt;</a>
	<%} %>
	
	<%-- 이전 페이지 링크(pno - 1) --%>
	<%if(pno > 1){ %>
	<a href="list.do?pno=<%=pno-1%><%=query%>">&lt;</a>
	<%} %>
	
	<%-- 현재페이지는 링크없이 빨강으로 출력, 아니면 링크 출력 --%>
	<%for(int i=startBlock; i <= endBlock; i++){ %>
		<%if(i == pno){ %>
			<font color="red"><%=i%></font>
		<%}else{ %>
			<a href="list.do?pno=<%=i%><%=query%>"><%=i%></a>
		<%} %> 
	<%} %>
	
	<%-- 다음 페이지 링크(pno + 1) --%>
	<%if(pno < pageCount){ %>
	<a href="list.do?pno=<%=pno+1%><%=query%>">&gt;</a>
	<%} %>
	
	<%-- 다음 구간 --%>
	<%if(endBlock < pageCount){ %>
	<a href="list.do?pno=<%=endBlock+1%><%=query%>">&gt;&gt;</a>
	<%}%>
	
</h4>

<jsp:include page="/template/footer.jsp"></jsp:include>






