<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/template/header.jsp"></jsp:include>

<h2>서버에서 알 수 없는 오류가 발생했습니다</h2>
<img src="<%=request.getContextPath()%>/image/500.png" width="800" height="400">

<jsp:include page="/template/footer.jsp"></jsp:include>