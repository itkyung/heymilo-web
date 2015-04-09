<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.heymilo.com/taglibs" prefix="heymilo" %>
<%@ page session="false" %>
<html>
<head>
	<title>헤이마일로</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/admin/product/productForm.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery/tinymce3/jquery.tinymce.js"></script>
	
	<c:choose>
		<c:when test="${!empty productInfo}">
			
		</c:when>
		<c:otherwise>
			
		</c:otherwise>
	</c:choose>
	
	
</head>
<body>
	<div class="container">
		<h3 class="page-header">상품 관리</h3>
		<form id="productForm" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
			<input type=hidden id="productId" name="productId" value="${product.id}"/>
			
			<ul id="productTab" class="nav nav-tabs">
				<li class="active"><a href="#tab-0">기본정보</a></li>
				<li><a href="#tab-1">상품설명</a></li>
			  	<li><a href="#tab-2">판매정보</a></li>
			</ul>
			<div class="tab-content">
				<div id="tab-0" class="tab-pane active hey-tab">
					<div class="form-group">
					    <label for="category" class="col-lg-2 control-label">카테고리(다중선택)</label>
					    <div class="col-lg-8">
					      	<select id="category" name="category" multiple class="form-control">
								<c:forEach var="row" items="${categories}">
									<c:choose>
										<c:when test="${heymilo:contains(row.id, product.categories)}">
											<option value="${row.id}" selected>${row.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${row.id}">${row.name}</option>
										</c:otherwise>
									</c:choose>  
									
								</c:forEach>
							</select>
					    </div>
					</div>
					<div class="form-group">
					    <label for="feature" class="col-lg-2 control-label">상품성분(다중선택)</label>
					    <div class="col-lg-8">
					      	<select id="feature" name="feature" multiple class="form-control">
					      		
								<c:forEach var="row" items="${features}">
									<c:choose>
										<c:when test="${heymilo:contains(row.id, product.features)}">
											<option value="${row.id}" selected>${row.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${row.id}">${row.name}</option>
										</c:otherwise>
									</c:choose>  
								</c:forEach>
							</select>
					    </div>
					</div>
					<div class="form-group">
					    <label for="exhibition" class="col-lg-2 control-label">기획전(다중선택)</label>
					    <div class="col-lg-8">
					      	<select id="exhibition" name="exhibition" multiple class="form-control">
					      		
								<c:forEach var="row" items="${exhibitions}">
									<c:choose>
										<c:when test="${heymilo:contains(row.id, product.exhibitions)}">
											<option value="${row.id}" selected>${row.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${row.id}">${row.name}</option>
										</c:otherwise>
									</c:choose>  
								</c:forEach>
							</select>
					    </div>
					</div>		
					<div class="form-group">
					    <label for="status" class="col-lg-2 control-label">판매상태</label>
					    <div class="col-lg-5">
					      	<select id="status" name="status" class="form-control">
					      		<option value="IN_SALE">판매중</option>
								<option value="STOP_SALE">판매중지</option>
							</select>
					    </div>
					</div>		
					<div class="form-group">
						<label for="name" class="col-lg-2 control-label">상품명</label>
					    <div class="col-lg-8">
					    	<input type="text" class="form-control" id="name" name="name" placeholder="상품명" value="${product.name}"
					    		data-counter="#nameCounter" data-text-length="50">
					    </div>
					    <div class="col-lg-2 height-sm">
							   <code id="nameCounter">0/50</code>
						</div>
					</div>
					<div class="form-group">
						<label for="productCode" class="col-lg-2 control-label">상품코드</label>
					    <div class="col-lg-5">
					    	<input type="text" class="form-control" id="productCode" name="productCode" placeholder="상품코드" value="${product.productCode}"
					    	data-counter="#codeCounter" data-text-length="20">
					    </div>
					    <div class="col-lg-2 height-sm">
							   <code id="codeCounter">0/20</code>
						</div>
					</div>		
					<div class="form-group">
						<label for="dueDate" class="col-lg-2 control-label">유효일자</label>
					    <div class="col-lg-5">
					    	<input type="text" class="form-control" id="dueDate" name="dueDate" value="${product.dueDate}">
					    </div>
					</div>	
					<div class="form-group">
						<label for="canSubscription" class="col-lg-2 control-label">정기구독가능</label>
					    <div class="col-lg-5">
					    	<c:choose>
					    	<c:when test="${product.canSubscription eq true}">
					    		<input type="checkbox" class="form-control" id="canSubscription" name="canSubscription" value="true" checked>
					    	</c:when>
					    	<c:otherwise>
					    		<input type="checkbox" class="form-control" id="canSubscription" name="canSubscription" value="true">
					    	</c:otherwise>
					    	</c:choose>
					    	
					    </div>
					</div>		
					<div class="form-group">
						<label for="mainImageUpload" class="col-lg-2 control-label">상품 이미지</label>
						<div class="col-lg-10">
							
							<!-------  이미지 업로드 영역 시작 ------>
									<div id="imageUploadWrapper">
										<div id="imageUploadLeft">
											<div class="imageInputRow">
												<span>대표 이미지 </span>
												<input type=hidden id="uploadNumber" name="uploadNumber" />
											</div>
											<div class="imageInputRow">
												<div class="img-file"> 
													<input type=file size="5" name="mainImageUpload" id="mainImageUpload"/> 
													<a href="javascript:showImage(true,0);"> 이미지 보기</a>
													<input type=hidden id="mainImagePath" name="mainImagePath" value="${product.mainImagePath}"/>
													<input type=hidden id="mainThumbnailPath" name="mainThumbnailPath" value="${product.mainThumbnailPath}"/>
												
												</div>
												<c:choose>
													<c:when test="${!empty product.mainImagePath}">
														<div id="mainShowBtn" class="imageShowBtn">
															<a href="javascript:showImage(true,0);"> 보기</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="mainShowBtn" class="hide imageShowBtn">
															<a href="javascript:showImage(true,0);"> 보기</a>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="imageInputRow">
												<span>추가 이미지 (최대 9개)</span>
											</div>
											<div class="imageInputRow">
												<div class="img-file"> 
													<input type=file size="10" name="subImageUpload[0]" id="subImageUpload0"/> 
													<a href="javascript:showImage(false,0);"> 보기</a>
													<input type=hidden id="subImagePath0" name="images[0]" value="${product.images[0].imagePath}"/>
													<input type=hidden id="subThumbnailPath0" value="${product.images[0].thumbnailPath}"/>
													
												</div>
												<c:choose>
													<c:when test="${!empty product.images[0].imagePath}">
														<div id="subShowBtn0" class="imageShowBtn">
															<a href="javascript:showImage(false,0);"> 보기</a>
															<a href="javascript:delImage(0,'${product.images[0].id}')">삭제</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="subShowBtn0" class="hide imageShowBtn">
															<a href="javascript:showImage(false,0);"> 보기</a>
															<a href="javascript:delImage(0)">삭제</a>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="imageInputRow">
												
												<div class="img-file"> 
													<input type=file size="10" name="subImageUpload[1]" id="subImageUpload1"/> 
													<a href="javascript:showImage(false,1);"> 보기</a>
													<input type=hidden id="subImagePath1" name="images[1]" value="${product.images[1].imagePath}"/>
													<input type=hidden id="subThumbnailPath1" value="${product.images[1].thumbnailPath}"/>
												</div>
												<c:choose>
													<c:when test="${!empty product.images[1].imagePath}">
														<div id="subShowBtn1" class="imageShowBtn">
															<a href="javascript:showImage(false,1);"> 보기</a>
															<a href="javascript:delImage(1,'${product.images[1].id}')">삭제</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="subShowBtn1" class="hide imageShowBtn">
															<a href="javascript:showImage(false,1);"> 보기</a>
															<a href="javascript:delImage(1)">삭제</a>
														</div>
													</c:otherwise>
												</c:choose>
												
											</div>
											<div class="imageInputRow">
												
												<div class="img-file"> 
													<input type=file size="10" name="subImageUpload[2]" id="subImageUpload2"/> 
													<a href="javascript:showImage(false,2);"> 보기</a>
													<input type=hidden id="subImagePath2" name="images[2]" value="${product.images[2].imagePath}"/>
													<input type=hidden id="subThumbnailPath2" value="${product.images[2].thumbnailPath}"/>
													
												</div>
												<c:choose>
													<c:when test="${!empty product.images[2].imagePath}">
														<div id="subShowBtn2" class="imageShowBtn">
															<a href="javascript:showImage(false,2);"> 보기</a>
															<a href="javascript:delImage(2,'${product.images[2].id}')">삭제</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="subShowBtn2" class="hide imageShowBtn">
															<a href="javascript:showImage(false,2);"> 보기</a>
															<a href="javascript:delImage(2)">삭제</a>
														</div>
													</c:otherwise>
												</c:choose>
												
											</div>
											<div class="imageInputRow">
												<div class="img-file"> 
													<input type=file size="10" name="subImageUpload[3]" id="subImageUpload3" /> 
													<a href="javascript:showImage(false,3);"> 보기</a>
													<input type=hidden id="subImagePath3" name="images[3]" value="${product.images[3].imagePath}"/>
													<input type=hidden id="subThumbnailPath3"  value="${product.images[3].thumbnailPath}"/>
													
												</div>
												<c:choose>
													<c:when test="${!empty product.images[3].imagePath}">
														<div id="subShowBtn3" class="imageShowBtn">
															<a href="javascript:showImage(false,3);"> 보기</a>
															<a href="javascript:delImage(3,'${product.images[3].id}')">삭제</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="subShowBtn3" class="hide imageShowBtn">
															<a href="javascript:showImage(false,3);"> 보기</a>
															<a href="javascript:delImage(3)">삭제</a>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="imageInputRow">
												<div class="img-file"> 
													<input type=file size="10" name="subImageUpload[4]" id="subImageUpload4" /> 
													<a href="javascript:showImage(false,4);"> 보기</a>
													<input type=hidden id="subImagePath4" name="images[4]" value="${product.images[4].imagePath}"/>
													<input type=hidden id="subThumbnailPath4" value="${product.images[4].thumbnailPath}"/>
													
												</div>
												<c:choose>
													<c:when test="${!empty product.images[4].imagePath}">
														<div id="subShowBtn4" class="imageShowBtn">
															<a href="javascript:showImage(false,4);"> 보기</a>
															<a href="javascript:delImage(4,'${product.images[4].id}')">삭제</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="subShowBtn4" class="hide imageShowBtn">
															<a href="javascript:showImage(false,4);"> 보기</a>
															<a href="javascript:delImage(4)">삭제</a>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="imageInputRow">
												<div class="img-file"> 
													<input type=file size="10" name="subImageUpload[5]" id="subImageUpload5" /> 
													<a href="javascript:showImage(false,5);"> 보기</a>
													<input type=hidden id="subImagePath5" name="images[5]" value="${product.images[5].imagePath}"/>
													<input type=hidden id="subThumbnailPath5" name="images[5].thumbnailPath" value="${product.images[5].thumbnailPath}"/>
												
												</div>
												<c:choose>
													<c:when test="${!empty product.images[5].imagePath}">
														<div id="subShowBtn5" class="imageShowBtn">
															<a href="javascript:showImage(false,5);"> 보기</a>
															<a href="javascript:delImage(5,'${product.images[5].id}')">삭제</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="subShowBtn5" class="hide imageShowBtn">
															<a href="javascript:showImage(false,5);"> 보기</a>
															<a href="javascript:delImage(5)">삭제</a>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="imageInputRow">
												<div class="img-file"> 
													<input type=file size="10" name="subImageUpload[6]" id="subImageUpload6" /> 
													<a href="javascript:showImage(false,6);"> 보기</a>
													<input type=hidden id="subImagePath6" name="images[6]" value="${product.images[6].imagePath}"/>
													<input type=hidden id="subThumbnailPath6" name="images[6].thumbnailPath" value="${product.images[6].thumbnailPath}"/>
													
												</div>
												<c:choose>
													<c:when test="${!empty product.images[6].imagePath}">
														<div id="subShowBtn6" class="imageShowBtn">
															<a href="javascript:showImage(false,6);"> 보기</a>
															<a href="javascript:delImage(6,'${product.images[6].id}')">삭제</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="subShowBtn6" class="hide imageShowBtn">
															<a href="javascript:showImage(false,6);"> 보기</a>
															<a href="javascript:delImage(6)">삭제</a>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="imageInputRow">
												<div class="img-file"> 
													<input type=file size="10" name="subImageUpload[7]" id="subImageUpload7" /> 
													<a href="javascript:showImage(false,7);"> 보기</a>
													<input type=hidden id="subImagePath7" name="images[7]" value="${product.images[7].imagePath}"/>
													<input type=hidden id="subThumbnailPath7" name="images[7].thumbnailPath" value="${product.images[7].thumbnailPath}"/>
													
												</div>
												<c:choose>
													<c:when test="${!empty product.images[7].imagePath}">
														<div id="subShowBtn7" class="imageShowBtn">
															<a href="javascript:showImage(false,7);"> 보기</a>
															<a href="javascript:delImage(7,'${product.images[7].id}')">삭제</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="subShowBtn7" class="hide imageShowBtn">
															<a href="javascript:showImage(false,7);"> 보기</a>
															<a href="javascript:delImage(7)">삭제</a>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="imageInputRow">
												<div class="img-file"> 
													<input type=file size="10" name="subImageUpload[8]" id="subImageUpload7" /> 
													<a href="javascript:showImage(false,8);"> 보기</a>
													<input type=hidden id="subImagePath8" name="images[8]" value="${product.images[8].imagePath}"/>
													<input type=hidden id="subThumbnailPath8" name="images[8].thumbnailPath" value="${product.images[8].thumbnailPath}"/>
													
												</div>
												<c:choose>
													<c:when test="${!empty product.images[8].imagePath}">
														<div id="subShowBtn8" class="imageShowBtn">
															<a href="javascript:showImage(false,8);"> 보기</a>
															<a href="javascript:delImage(8,'${product.images[8].id}')">삭제</a>
														</div>
													</c:when>
													<c:otherwise>
														<div id="subShowBtn8" class="hide imageShowBtn">
															<a href="javascript:showImage(false,8);"> 보기</a>
															<a href="javascript:delImage(8)">삭제</a>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											
											
											
										</div>
										<div id="imageUploadRight">
											<div id="imageOriginalWrapper">
												<div class="pull-right" style="height:70px;">
													<ul class="list-unstyled">
														
													</ul>
												</div>
												<div id="imageOriginal" style="text-align:center;">
													<c:choose>
														<c:when test="${empty product.mainImagePath}">
															<img src="${pageContext.request.contextPath}/resources/images/1.5/sub/sub_img_bg.png"/>
														</c:when>
														<c:otherwise>
															<img src="${product.mainImagePath}" alt="제품 이미지" width="450" height="450" class="img-rounded"/>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
									</div>
									<!--  이미지 업로드 영역 끝 -->
							
						</div>
					</div>
				</div>
				<div id="tab-1" class="tab-pane hey-tab">
					<div class="form-group">
						<label for="briefDesc" class="col-lg-2 control-label">상품간단정보</label>
					    <div class="col-lg-10">
					    	<textarea id="briefDesc" name="briefDesc" cols="130" rows="8">${product.briefDesc}</textarea>
					    </div>
					</div>					
					<div class="form-group">
						<label for="htmlDesc" class="col-lg-2 control-label">상품상세정보</label>
					    <div class="col-lg-10">
					    	<textarea id="htmlDesc" name="htmlDesc">${product.htmlDesc}</textarea>
					    </div>
					</div>		
				</div>
				<div id="tab-2" class="tab-pane hey-tab">
					<div class="form-group">
						<label for="productPrice" class="col-lg-2 control-label">상품가격</label>
					    <div class="col-lg-4">
					    	<input type="text" class="form-control" id="productPrice" name="productPrice" value="${heymilo:formatNumber(product.productPrice)}" 
					    	numberonly="true"/>
					    </div>
					    <div class="col-lg-2 height-sm">
					    	원
					    </div>
					</div>		
					<div class="form-group">
						<label for="miloPrice" class="col-lg-2 control-label">마일로가격</label>
					    <div class="col-lg-4">
					    	<input type="text" class="form-control" id="miloPrice" name="miloPrice" value="${heymilo:formatNumber(product.miloPrice)}" numberonly="true"/>
					    </div>
					     <div class="col-lg-2 height-sm">
					    	원
					    </div>
					</div>	
					<div class="form-group">
						<label for="totalCount" class="col-lg-2 control-label">등록수량</label>
					    <div class="col-lg-4">
					    	<input type="text" class="form-control" id="totalCount" name="totalCount" value="${product.totalCount}" numberonly="true"/>
					    </div>
					     <div class="col-lg-2 height-sm">
					    	개
					    </div>
					</div>
					<div class="form-group">
						<label for="miloPrice" class="col-lg-2 control-label">재고수량</label>
					    <div class="col-lg-4">
					    	<input type="text" class="form-control" value="${stockCount}" readonly="readonly"/>
					    </div>
					    <div class="col-lg-2 height-sm">
					    	개
					    </div>
					</div>
				</div>
			</div>
			<div class="info-section-bottom">
				<div class="container align-center">
					<button id="btnSaveInfo" type="button" class="btn og-btn-primary" onclick="doSubmit();">상품 등록</button>
					<button type="button" class="btn btn-default">취 소</button>
				</div>
			</div>
			</form>
		
	</div>	
</body>
</html>
