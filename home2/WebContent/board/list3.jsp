<%@page import="home.beans.BoardDto"%>
<%@page import="home.util.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//페이징 도구에게 요청정보를 전달하며 처리를 부탁
	Paging p = new Paging(request);
	p.calculate();
%>    

<jsp:include page="/template/header.jsp"></jsp:include>

<h5><%=p%></h5>

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
		<%for(BoardDto bdto : p.getList()){ %>
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
	<%if(!p.isFirstBlock()){ %>
		<%-- 이전 구간 링크 --%>
		<a href="list.do?<%=p.getPrevBlock()%>">&lt;&lt;</a>
	<%} %>
	
	<%-- 이전 페이지 링크(pno - 1) --%>
	<%if(!p.isFirstPage()){ %> 
	<a href="list.do?<%=p.getPrevPage()%>">&lt;</a>
	<%} %>
	
	<%-- 현재페이지는 링크없이 빨강으로 출력, 아니면 링크 출력 --%>
	<%for(int i=p.getStartBlock(); i <= p.getEndBlock(); i++){ %>
		<%if(p.isCurrentPage(i)){ %>  
			<font color="red"><%=i%></font>
		<%}else{ %>
			<a href="list.do?<%=p.getPage(i)%>"><%=i%></a>
		<%} %> 
	<%} %> 
	
	<%-- 다음 페이지 링크(pno + 1) --%>
	<%if(!p.isLastPage()){ %>
	<a href="list.do?<%=p.getNextPage()%>">&gt;</a>
	<%} %>
	
	<%-- 다음 구간 --%>
	<%if(!p.isLastBlock()){ %> 
	<a href="list.do?<%=p.getNextBlock()%>">&gt;&gt;</a>
	<%}%>
	
</h4>

<jsp:include page="/template/footer.jsp"></jsp:include>






