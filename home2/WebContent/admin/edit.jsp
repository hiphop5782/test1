<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/template/header.jsp"></jsp:include>

<h1>회원 정보 수정</h1>

<form action="edit.do" method="post">
<table border="1" width="400">
	<tbody>
		<tr>
			<th width="30%">회원번호</th>
			<td>${mdto.no}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
<%-- 				<input type="text" name="email" value="<%=mdto.getEmail()%>" readonly> --%>
				${mdto.email}
				<input type="hidden" name="email" value="${mdto.email}">
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="name" value="${mdto.name}" required>
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>
				<input type="date" name="birth" value="${mdto.birth}" required>
			</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>
				<input type="text" name="phone" value="${mdto.phone}">
			</td>
		</tr>
		<tr>
			<th>확인 질문</th>
			<td>
				<input type="text" name="question" value="${mdto.question}" required>
			</td>
		</tr>
		<tr>
			<th>확인 답변</th>
			<td>
				<input type="text" name="answer" value="${mdto.answer}" required>
			</td>
		</tr>
		<tr>
			<th>권한</th>
			<td>
				<select name="auth">
					<option ${mdto.auth eq '일반'?'selected':''}>일반</option>
					<option ${mdto.auth eq 'VIP'?'selected':''}>VIP</option>
					<option ${mdto.auth eq '관리자'?'selected':''}>관리자</option>
				</select>
				
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<th colspan="2">
				<input type="submit" value="수정하기">
			</th>
		</tr>
	</tfoot>
</table>
</form>

<jsp:include page="/template/footer.jsp"></jsp:include>