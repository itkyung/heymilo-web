<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>헤이마일로</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/admin/user/listUsers.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="page-header">회원 관리</h3>
		
		<form id="searchForm" class="form-horizontal" role="form"  method="post">
			<div class="tab-content">
				<div class="tab-pane active opengarden-tab">
					<div class="form-group border-bottom">
						<label class="col-lg-2 control-label">키워드</label>
						<div class="col-lg-10 og-form-input">
							<input type="text" class="span2 form-control input-sm" id="keyword" name="keyword" placeholder="회원명 또는 로그인아이디"/>
						 </div>
					</div>
					<div class="form-group border-bottom">
						<label class="col-lg-2 control-label">회원상태</label>
						<div class="col-lg-10 og-form-input">
							<div class="col-lg-10">
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="ALL" checked> 전체
								 </label>
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="WAITING"> 인증대기
								 </label>
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="ACTIVE"> 인증완료
								 </label>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			<div class="info-section">
				<div class="container align-center">
					<button type="button" id="btnSearch" class="btn og-btn-primary">검 색</button>
				</div>
			</div>
		</form>
		<br/>
		
		<form id="resultTable">
			<div class="search-result-title">
				<div class="pull-left">
					검색결과 : 총 <code id="totalCount"> 0</code> 개.
				</div>
			</div>
			<table id="searchResult" class="display table-bordered" cellspacing="0" width="100%">
				<thead>
		            <tr>
		                <th>이름</th>
		                <th>로그인아이디</th>
		                <th>상태</th>
		                <th>가입일</th>
		                <th>마지막 로그인일</th>
		            </tr>
		        </thead>
		        
			</table>
			
		</form>
	</div>
		
</body>
</html>
