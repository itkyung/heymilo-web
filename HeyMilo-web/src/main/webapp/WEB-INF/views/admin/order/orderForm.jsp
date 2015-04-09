<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.heymilo.com/taglibs" prefix="heymilo" %>
<%@ page session="false" %>
<html>
<head>
	<title>헤이마일로</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/admin/order/orderForm.js"></script>
	
	<c:choose>
		<c:when test="${!empty orderInfo}">
			
		</c:when>
		<c:otherwise>
			
		</c:otherwise>
	</c:choose>
	
	
</head>
<body>
	<div class="container">
		<h3 class="page-header">주문 관리</h3>
		<form id="orderForm" class="form-horizontal" role="form" method="post">
			<input type=hidden id="orderId" name="orderId" value="${orderInfo.id}"/>
			<div class="form-group">
			    <label for="category" class="col-lg-2 control-label">오더번호</label>
			    <div class="col-lg-8">
			      	<p class="text-left">${orderInfo.orderNo}</p>
			    </div>
			</div>
			<div class="form-group">
			    <label for="feature" class="col-lg-2 control-label">주문상태</label>
			    <div class="col-lg-4">
			      	<select id="orderStatus" name="orderStatus" class="form-control">
						<c:forEach var="row" items="${nextStatusList}">
							<c:choose>
								<c:when test="${row eq orderInfo.status}">
									<option value="${row}" selected>${row.desc}</option>
								</c:when>
								<c:otherwise>
									<option value="${row}">${row.desc}</option>
								</c:otherwise>
							</c:choose>  
						</c:forEach>
					</select>
			    </div>
			</div>
			<div class="form-group">
			    <label for="feature" class="col-lg-2 control-label">주문일</label>
			    <div class="col-lg-8">
			      	${heymilo:formatDateTime(orderInfo.createdAt)}
			    </div>
			</div>
			<div class="form-group">
			    <label for="feature" class="col-lg-2 control-label">최종상태변경일</label>
			    <div class="col-lg-8">
			      	${heymilo:formatDateTime(orderInfo.updatedAt)}
			    </div>
			</div>
			<div class="form-group">
			    <label for="feature" class="col-lg-2 control-label">총 금액</label>
			    <div class="col-lg-8">
			      	${heymilo:formatNumber(orderInfo.totalPrice.doubleValue())} 원
			    </div>
			</div>
			<div class="form-group">
			    <label for="feature" class="col-lg-2 control-label">운송장번호</label>
			    <div class="col-lg-4">
			      	<input type="text" class="form-control" id="shippingNo" name="shippingNo" placeholder="운송장번호" value="${orderInfo.shippingNo}"
					    data-counter="#shippingNoCounter" data-text-length="20">
			    </div>
			    <div class="col-lg-2 height-sm">
					   <code id="shippingNoCounter">0/20</code>
				</div>
			</div>
			<div class="form-group">
			    <label for="feature" class="col-lg-2 control-label">택배사</label>
			    <div class="col-lg-4">
			      	<select id="deliveryCompany" name="deliveryCompany" class="form-control">
			      		<option value=""></option>
			      		<c:forEach var="row" items="${deliveryCompany}">
							<c:choose>
								<c:when test="${row eq orderInfo.deliveryCompany}">
									<option value="${row}" selected>${row.desc}</option>
								</c:when>
								<c:otherwise>
									<option value="${row}">${row.desc}</option>
								</c:otherwise>
							</c:choose>  
						</c:forEach>
					</select>
			    </div>
			</div>
			<div class="form-group">
			    <label for="status" class="col-lg-2 control-label">배송지 정보</label>
			    <div class="col-lg-8">
			      	<address>
			      		<strong>${orderInfo.zipCode}</strong><br>
			      		${orderInfo.address} ${orderInfo.detailAddress}<br>
			      		<abbr title="Phone">P:</abbr> ${orderInfo.phone1} , ${orderInfo.phone2} <br>
			      		${orderInfo.receiveName}
			      	</address>
			    </div>
			</div>			
			<div class="info-section-bottom">
				<div class="container align-center">
					<button id="btnSaveInfo" type="button" class="btn og-btn-primary" onclick="doSubmit();">수정</button>
				</div>
			</div>
		</form>
		<br>
		<div class="dataTables_scroll">
			<table id="orderItem" class="display table-bordered dataTable no-footer" cellspacing="0" width="100%">
				<thead>
		            <tr>
		                <th>제품명</th>
		                <th width="10%">마일로가</th>
		                <th width="10%">구매수량</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach var="item" items="${orderInfo.items}">
			        	<tr role="row">
			        		<td>${item.product.name}</td>
			        		<td>${heymilo:formatNumber(item.itemPrice.doubleValue())} 원</td>
			        		<td>${item.itemCount} 개</td>
			        	</tr>
		        	</c:forEach>
		        </tbody>
			</table>
		</div>
	</div>	
</body>
</html>
