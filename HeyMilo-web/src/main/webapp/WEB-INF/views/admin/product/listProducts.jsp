<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>헤이마일로</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/admin/product/listProducts.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="page-header">상품 관리</h3>
		
		<form id="searchForm" class="form-horizontal" role="form"  method="post">
			<div class="tab-content">
				<div class="tab-pane active opengarden-tab">
					<div class="form-group border-bottom">
						<label class="col-lg-2 control-label">상품명</label>
						<div class="col-lg-10 og-form-input">
							<input type="text" class="span2 form-control input-sm" id="searchKeyword" name="keyword" placeholder="상품명입력"/>
						 </div>
					</div>
					<div class="form-group border-bottom">
						<label class="col-lg-2 control-label">상품상태</label>
						<div class="col-lg-10 og-form-input">
							<div class="col-lg-10">
								<label class="radio-inline">
								    <input type="radio" name="status" id="status" value="ALL" checked> 전체
								 </label>
								 <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="IN_SALE"> 판매중
								 </label>
								  <label class="radio-inline">
								    <input type="radio" name="status" id="status" value="STOP_SALE"> 판매대기
								 </label>
							</div>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-lg-2 control-label">추가옵션</label>
						<div class="col-lg-10 og-form-input">
							<label class="checkbox-inline">
								<input type="checkbox" name="newProduct" id="newProduct" value="true"> 신제품
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="canSubscription" id="canSubscription" value="true"> 정기구독가능
							</label>
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
				<div class="pull-right">
					<button  type="button" class="btn og-btn-warning" onclick="addProduct();">상품 추가</button>
				</div>
			</div>
			<table id="searchResult" class="display table-bordered" cellspacing="0" width="100%">
				<thead>
		            <tr>
		                <th></th>
		                <th>이미지</th>
		                <th>상품명</th>
		                <th>새상품</th>
		                <th>정기구독가능</th>
		                <th>마일로가</th>
		                <th>상품가</th>
		                <th>상태</th>
		                <th>최종수정일</th>
		            </tr>
		        </thead>
		        
			</table>
			
		</form>
	</div>
		
</body>
</html>
