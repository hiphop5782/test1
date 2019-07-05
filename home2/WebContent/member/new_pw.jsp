<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	비밀번호 변경 페이지 
	 - new_pw.do라는 서블릿에게 이메일, 새로운 비밀번호를 전달한다
	 - 이메일은 요청 정보 파라미터에서 추출 가능 --> 자동 첨부 처리
	 - 비밀번호는 사용자가 입력
--%>

<jsp:include page="/template/header.jsp"></jsp:include>

<h1>비밀번호 변경</h1>
<form class="form" action="new_pw.do" method="post">
	<%-- hidden은 사용자에게 표시되지 않으면서 전송이 가능 --%>
	<input type="hidden" name="email" value="${param.email}">
	<input type="password" name="pw" placeholder="신규 비밀번호" required>
	<input type="submit" value="변경하기">
</form>

<jsp:include page="/template/footer.jsp"></jsp:include>







