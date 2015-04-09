<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>헤이마일로</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/admin/order/listOrders.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="page-header">일반 주문 관리</h3>
		
		<form id="searchForm" class="form-horizontal" role="form"  method="post">
			<div class="tab-content">
				<div class="tab-pane active opengarden-tab">
					<div class="form-group border-bottom">
						<label class="col-lg-2 control-label">오더번호</label>
						<div class="col-lg-10 og-form-input">
							<input type="text" class="span2 form-control input-sm" id="orderNo" name="orderNo" placeholder="오더번호입력"/>
						 </div>
					</div>
					<div class="form-group border-bottom">
						<label class="col-lg-2 control-label">오더상태</label>
						<div class="col-lg-10 og-form-input">
							<div class="col-lg-10">
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="ALL" checked> 전체
								 </label>
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="ORDERD"> 결재완료
								 </label>
								  <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="SHIPPING"> 배송중
								 </label>
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="COMPLETED"> 배송완료
								 </label>
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="CANCELED"> 취소완료
								 </label>
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="CANCEL_REQ"> 취소요청
								 </label>
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="RETURN_REQ"> 반품요청
								 </label>
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="RETURNED"> 반품완료
								 </label>
							</div>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-lg-2 control-label">고객아이디(이메일)</label>
						<div class="col-lg-10 og-form-input">
							<input type="text" class="span2 form-control input-sm" id="buyerId" name="buyerId" placeholder="고객 로그인 아이디 입력"/>
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
		                <th>주문번호</th>
		                <th>상태</th>
		                <th>고객이름</th>
		                <th>상품 Brief</th>
		                <th>주문금액</th>
		                <th>생성일</th>
		                <th>최종수정일</th>
		            </tr>
		        </thead>
		        
			</table>
			
		</form>
	</div>
		
</body>
</html>
