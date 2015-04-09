<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
	<title>헤이마일로</title>
</head>
<body>
	<div class="container">
		<center>
			<p style="margin-bottom:30px; font-size:14pt; color:#d34b46">인증성공!</p>
			<p style="font-size:12pt">메일 인증이 완료되었습니다.</p>
			<p style="font-size:12pt">이제, 헤이마일로에 로그인해주세요.</p>
			<a href="${pageContext.request.contextPath}/login/loginForm" class="btn btn-primary" >로그인하러가기</a>
		</center>
	</div>
</body>

</html>
