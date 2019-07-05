<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
	이 페이지는 "로그인 된 유저"가 "한번 더 인증" 할 때 사용
 --%>
    
<jsp:include page="/template/header.jsp"></jsp:include>

<h2>비밀번호 확인</h2>

<form class="form" action="check.do" >
	<%-- 목적지 정보인 go 파라미터를 다음 페이지로 전송 --%>
	<input type="hidden" name="go" value="<%=request.getParameter("go")%>">

	<input type="password" name="pw" placeholder="현재 비밀번호" required>
	<input type="submit" value="확인">
</form>

<%-- error가 있을 경우 출력 --%>
<c:if test="${not empty param.error}">
	<h4><font color="red">비밀번호가 맞지 않습니다.</font></h4>
</c:if>

<jsp:include page="/template/footer.jsp"></jsp:include>




