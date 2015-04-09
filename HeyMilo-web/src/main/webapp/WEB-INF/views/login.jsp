<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
	<title>헤이마일로</title>
	<c:url value="/login/registAction" var="registActionUrl"/>
	<script>
	
	$(document).ready(function(){
		$('#pwd').keyup(function(e) {
		    if (e.keyCode == 13) {
		    	 _login();
		    }
		});
	});
	
		function _login() {
			var loginId = $("#loginId").val();
			if(loginId.length == 0){
				alert("아이디를 입력하세요.");
				return;
			}
			var password = $("#pwd").val();
			if(password.length == 0){
				alert("비밀번호를 입력하세요.");
				return;
			}
			$("#loginForm").submit();
		}
		
		function _loginWithFacebook() {
			alert("아직 작업중입니다.");
		}
		
		function _regist() {
			$("#registModal").on('show.bs.modal',function(){
				
			}).modal('show');
		}
		
		function registAction() {
			var registLoginId = $("#registLoginId").val();
			if(registLoginId.length == 0){
				alert("이메일 주소를 입력하세요.");
				return;
			}
			var registPassword = $("#registPassword").val();
			var registPasswordConfirm = $("#registPasswordConfirm").val();
			if(registPassword.length == 0 || registPasswordConfirm.length == 0){
				alert("비밀번호를 입력하세요.");
				return;
			}	
			
			if(registPassword != registPasswordConfirm){
				alert("비밀번호와 비밀번호 확인의 값이 서로 다릅니다.");
				return;
			}
			
			var name = $("#registName").val();
			if(name.length == 0){
				alert("이름을 입력하세요.");
				return;
			}
			
			var formData = $("#registForm").serialize();
			$.ajax({
				type : "POST",
				url : "${registActionUrl}",
				data : formData,
				success : function(json,status) {
					var success = json.success;
					if(success){
						alert("입력하신 이메일계정으로 인증메일이 발송되었습니다. 인증메일을 확인하셔서 가입을 완료하세요.");
						$("#registModal").modal('hide');
					}else{
						alert("가입중에 에러가 발생하였습니다.시스템 관리자에게 문의하세요.");
					}
				},
				error : function(data,status) {
					alert("Error");
				}
			});
		}
		
		function registWithFacebook(){
			alert("아직 작업중입니다.");
		}
		
	</script>
</head>
<body>
	<c:url value="/loginAction" var="loginUrl"/>
	<div class="container">
		<h3>헤이마일로 로그인</h3>
		<h5>Admin 테스트 계정 (admin / admin!@#$)</h5>
		<form id="loginForm" role="form" action="${loginUrl}" method="POST">
		  <div class="form-group">
		    <label for="loginId">아이디(이메일 주소)</label>
		    <input type="email" class="form-control" id="loginId" name="loginId" placeholder="Enter email">
		  </div>
		  <div class="form-group">
		    <label for="pwd">비밀번호</label>
		    <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Password">
		  </div>
		  <div class="checkbox">
		    <label>
		      <input type="checkbox"> Remember me
		    </label>
		  </div>
		  <p>
		  	 <a href="javascript:_login();" class="btn btn-primary" role="button">로그인</a>
		  	 <a href="javascript:_loginWithFacebook();" class="btn btn-default" role="button">Facebook으로 로그인</a>
		  	 <a href="javascript:_regist();" class="btn btn-default" role="button">회원가입</a>
		  </p>
		</form>
		
	</div>
	
	<div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="h4#registModal" aria-hidden="true">
		
		<div class="modal-dialog">
		    <div class="modal-content">
		    	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		        	<h4 class="modal-title">헤이마일로 회원가입</h4>
		      	</div>
		      	<form id="registForm" class="form-horizontal" role="form" action="${registActionUrl}" method="POST">
		     		<div class="modal-body">
					
					  <div class="form-group">
					    <label for="registLoginId" class="col-lg-2 control-label">이메일</label>
					    <div class="col-lg-10">
					      <input type="email" class="form-control" id="registLoginId" name="registLoginId" placeholder="Email">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="registPassword" class="col-lg-2 control-label">비밀번호</label>
					    <div class="col-lg-10">
					      <input type="password" class="form-control" id="registPassword" name="registPassword" placeholder="Password">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="registPasswordConfirm" class="col-lg-2 control-label">비밀번호 확인</label>
					    <div class="col-lg-10">
					      <input type="password" class="form-control" id="registPasswordConfirm" name="registPasswordConfirm" placeholder="Confirm Password">
					    </div>
					  </div>
					 <div class="form-group">
					    <label for="registName" class="col-lg-2 control-label">이름</label>
					    <div class="col-lg-10">
					      <input type="text" class="form-control" id="registName" name="registName" placeholder="이름">
					    </div>
					  </div>
					</div>
					<div class="modal-footer">
				      	<button type="button" id="btnRegist" class="btn btn-primary" onclick="registAction();">가입하기</button>
				      	<button type="button" id="btnRegistWithFacebook" class="btn btn-default" onclick="registWithFacebook();">Facebook으로 가입하기</button>
				        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</form>
 			 </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->				
</body>
</html>