<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/template/header.jsp"></jsp:include>

<h2>비밀번호 변경이 완료되었습니다</h2>
<h3><a href="login.do">로그인 할래요?</a></h3>
<h3><a href="<%=request.getContextPath()%>/index.jsp">메인으로 갈래요?</a></h3>

<jsp:include page="/template/footer.jsp"></jsp:include>