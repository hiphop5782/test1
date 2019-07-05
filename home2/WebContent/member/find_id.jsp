<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/template/header.jsp"></jsp:include>

<h2>아이디 찾기</h2>

<form class="form form-vertical-line" action="find_id.do" method="get">
	<fieldset class="w40"> 
		<input type="text" name="name" placeholder="이름" required>
		<input type="date" name="birth" placeholder="생년월일" required>
		<input type="text" name="phone" placeholder="전화번호">
		<input type="submit" value="찾기">
	</fieldset>
</form>

<jsp:include page="/template/footer.jsp"></jsp:include>




