<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.context.*" %>
<%@ page import="org.springframework.web.context.support.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Script-Type" content="text/javascript" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>헤이마일로</title>
	<script>
		
		var _requestPath = "${pageContext.request.contextPath}";
		var _imageServerPath = "${_imageServerPath}";
		
		
	</script>	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery/smoothness/jquery-ui-1.9.1.custom.min.css"/>	
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css"/>	
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/e9421181788/integration/bootstrap/3/dataTables.bootstrap.css"/>	
	
	
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap/datepicker.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tempadmin.css"/>	
	
	
	<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery/jquery-ui-1.9.1.custom.min.js"></script>
		
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery/jquery.masonry.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery/jquery.imagesloaded.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery/jquery.number.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery/jquery.tmpl.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery/jqGrid-4.4.1/js/i18n/grid.locale-kr.js"></script>
	<script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="//cdn.datatables.net/plug-ins/e9421181788/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/bootstrap/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/bootstrap/bootstrap-datepicker.kr.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/bootstrap/bootbox.min.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/common.js"></script>
	
	
	
	<decorator:head />
</head>


<c:url value="/logout" var="logoutUrl"/>
<c:url value="/login/loginForm" var="loginUrl"/>

<body>

	<div id="main-container">
		<div id="main-header">
			<div class="container main-header-inner">
				
				<h3><a href="${pageContext.request.contextPath}/" class="header-logo"> HeyMilo 로고</a></h3>
				
				<div class="login-area">
					<c:choose>
						<c:when test="${empty _currentUser}">
							<a href="${loginUrl}">로그인</a>
						</c:when>
						<c:otherwise>
							${_currentUser.name}님. 환영합니다. <a href="${logoutUrl}">로그아웃</a>
						</c:otherwise>
					</c:choose>
				</div>
				
			</div>
		</div>
		<div id="main-body-wrapper">
			<div class="menu-bg">
				<nav class="navbar navbar-default container opengarden-nav" role="navigation">
				  <div class="container-fluid">
				    <!-- Brand and toggle get grouped for better mobile display -->
				    <div class="navbar-header">
				      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				        <span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				      </button>
				     
				    </div>
				
				    <!-- Collect the nav links, forms, and other content for toggling -->
				    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				      <ul class="nav navbar-nav">
				      	
			      		<li class="dropdown">
				        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">마스터 관리</a>
				        	<ul class="dropdown-menu">
					            <li><a href="${pageContext.request.contextPath}/admin/listProductCategories");">상품 카테고리</a></li>
					            <li><a href="${pageContext.request.contextPath}/admin/listExhibitions");">기획전</a></li>
					            <li><a href="${pageContext.request.contextPath}/admin/listFeatures");">상품 성분</a></li>
					        </ul>
				        </li>
				        <li class="dropdown">
				        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">상품 관리</a>
				        	<ul class="dropdown-menu">
					            <li><a href="${pageContext.request.contextPath}/admin/listProducts");">상품리스트</a></li>
					            <li><a href="${pageContext.request.contextPath}/admin/manageStocks");">재고관리</a></li>
					            <li><a href="${pageContext.request.contextPath}/admin/productForm");">상품 등록</a></li>
					        </ul>
				        </li>
				        <li class="dropdown">
				        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">판매관리</a>
				        	<ul class="dropdown-menu">
					            <li><a href="${pageContext.request.contextPath}/admin/listOrders">일반주문관리</a></li>
					            <li><a href="">정기주문관리</a></li>
					         </ul>
				        </li>
				      	<li class="dropdown">
				        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">회원관리</a>
				        	<ul class="dropdown-menu">
					            <li><a href="${pageContext.request.contextPath}/admin/listUsers">가입한 회원</a></li>
					         </ul>
				        </li>
				        <li class="dropdown">
				        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">통계</a>
				        	<ul class="dropdown-menu">
					            <li><a href="">매출 통계</a></li>
					         </ul>
				        </li>
				      </ul>
				     
				    </div><!-- /.navbar-collapse -->
				  </div><!-- /.container-fluid -->
				</nav>
			</div>
				<decorator:body />
			
			
		</div>
		<div id="main-footer">
			
				<%@ include file="/WEB-INF/views/decorators/admin/footer.jspf" %>
			
		</div>
		
	</div>
</body>

</html>