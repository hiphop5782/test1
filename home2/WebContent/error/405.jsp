<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/template/header.jsp"></jsp:include>

<h2>지원하지 않는 방식의 요청입니다</h2>
<img src="<%=request.getContextPath()%>/image/405.png" width="800" height="400">

<jsp:include page="/template/footer.jsp"></jsp:include>