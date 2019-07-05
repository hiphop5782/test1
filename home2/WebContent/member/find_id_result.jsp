<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/template/header.jsp"></jsp:include>

<c:choose>
	<c:when test="${empty email}">
		<h2>찾으시는 정보의 아이디가 없습니다</h2>
	</c:when>
	<c:otherwise>
		<h2>당신의 아이디는 ${email} 입니다</h2>
	</c:otherwise>
</c:choose>

<h3><a href="login.do">다시 로그인 할래요?</a></h3>
<h3><a href="find_id.do">아이디 찾으러 가실래요?</a></h3>
<h3><a href="find_pw.do">비밀번호 찾으러 가실래요?</a></h3>
<h3><a href="${pageContext.request.contextPath}">메인으로 갈래요?</a></h3>

<jsp:include page="/template/footer.jsp"></jsp:include>


