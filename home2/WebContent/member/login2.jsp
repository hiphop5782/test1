<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//saveId라는 이름의 쿠키값을 추출
	Cookie[] array = request.getCookies();
	String value = null;
	if(array != null){
		for(Cookie c : array){
			if(c.getName().equals("saveId")){
				value = c.getValue();
				break;
			}
		}
	}
%>
    
<jsp:include page="/template/header.jsp"></jsp:include>

	<h4>value = <%=value%></h4>

	<h2>
		<font color="red">L</font> 
		<font color="orange">o</font>
		<font color="yellow">g</font>
		<font color="green">i</font>
		<font color="blue">n</font>
	</h2>
	
	<%-- 
	(주의) 테이블과 폼을 같이 사용할 때는 th 안쪽이나 테이블 바깥에 폼구현 
	--%>
	
	<form action="login.do" method="post">
	<table>
		<tbody>
			<tr><td>
			<%--
				<%if(value == null){ %>
				<input type="text" name="email" placeholder="Email" required>
				<%}else{ %>
				<input type="text" name="email" placeholder="Email" value="<%=value%>" required>
				<%} %>
			 --%>
			 	<input type="text" name="email" placeholder="Email" value="<%=value==null?"":value %>" required>
			</td></tr>
			<tr><td>
				<input type="password" name="pw" placeholder="Password" required>
			</td></tr>
			<tr><td>
			<%-- 
				<%if(value == null){ %>
				<input type="checkbox" name="remember">
				<%}else{ %>
				<input type="checkbox" name="remember" checked>
				<%} %>
			--%>
			<input type="checkbox" name="remember" <%=value==null?"":"checked"%>>
				아이디 저장하기
			</td></tr>
			<tr><td align="center">
				<input type="submit" value="로그인">
			</td></tr>
			<tr><td>
				<a href="find_id.do">아이디를 찾고싶어요</a>
			</td></tr>
			<tr><td>
				<a href="find_pw.do">비밀번호를 찾고싶어요</a>
			</td></tr>
		</tbody>
	</table>
	</form>
<jsp:include page="/template/footer.jsp"></jsp:include>






