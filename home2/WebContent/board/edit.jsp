<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<jsp:include page="/template/header.jsp"></jsp:include>

<h1>글 수정하기</h1>

<form action="edit.do" method="post">

<%-- hidden은 반드시 값이 있어야 합니다 --%>
<input type="hidden" name="no" value="${no}">

<table border="1" width="700">
	<tbody>
		<tr>
			<td>
				<select name="head">
					<option value="">선택하세요</option>
					<option>정보</option>
					<option>유머</option>
					<option>자유</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="title" placeholder="제목" required
						size="70" value="${bdto.title}">
			</td>
		</tr>
		<tr>
			<td>
				<textarea name="content" placeholder="내용" required
					rows="10" cols="100">${bdto.content}</textarea>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr align="right">
			<td><input type="submit" value="글 수정"></td>
		</tr>
	</tfoot>
</table>
</form>

<jsp:include page="/template/footer.jsp"></jsp:include>

