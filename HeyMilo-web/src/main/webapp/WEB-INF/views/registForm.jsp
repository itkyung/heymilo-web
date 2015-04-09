<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
	<title>헤이마일로</title>
	
</head>
<body>
	<div class="container">
		<H3>회원가입</H3>
		<form class="form-horizontal" role="form">
		  <div class="form-group">
		    <label for="inputEmail1" class="col-lg-2 control-label">Email</label>
		    <div class="col-lg-10">
		      <input type="email" class="form-control" id="inputEmail1" placeholder="Email">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword1" class="col-lg-2 control-label">Password</label>
		    <div class="col-lg-10">
		      <input type="password" class="form-control" id="inputPassword1" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-lg-offset-2 col-lg-10">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> Remember me
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-lg-offset-2 col-lg-10">
		      <a href="" class="btn btn-primary" role="button">가입하기</a>
		      <a href="" class="btn btn-default" role="button">Facebook으로 가입하기</a>
		    </div>
		  </div>
		</form>
	
	
	</div>
</body>
</html>
