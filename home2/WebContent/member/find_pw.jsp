<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 비밀번호 확인 입력창 --%>
<jsp:include page="/template/header.jsp"></jsp:include>

<%-- error 파라미터 유무에 따라 오류메시지를 출력 --%>
<c:if test="${not empty param.error}">
	<h4>
		<font color="red">
			입력하신 정보에 해당하는 회원이 존재하지 않습니다	
		</font>
	</h4>
</c:if>

<h2>비밀번호 찾기</h2>
<form class="form form-vertical-line" action="find_pw.do" method="post">
	<fieldset class="w40">
		<input type="text" name="email" placeholder="이메일" required>
		<input type="text" name="question" placeholder="비밀번호 확인 질문" required>
		<input type="text" name="answer" placeholder="비밀번호 확인 답변" required>
		<input type="submit" value="찾기">
	</fieldset>
</form>

<jsp:include page="/template/footer.jsp"></jsp:include>

