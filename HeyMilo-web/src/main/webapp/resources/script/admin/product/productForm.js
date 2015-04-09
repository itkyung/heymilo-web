var manufactureMap = {};


$(document).ready(function(){
	
	$(":text").maxlength();
	
	
	$('#productTab a').click(function (e) {
		  e.preventDefault();
		  $(this).tab('show');
	});
	
	$("#dueDate").datepicker({
		showOn : 'button',
		dateFormat : 'yy-MM-dd',
		buttonImage:  _requestPath + "/resources/images/calendar.png",
		buttonImageOnly: true,
		changeYear : true,
		changeMonth : true,
		minDate : '+0D',
		dayNamesMin : ['일','월','화','수','목','금','토'],
		monthNames : ['01','02','03','04','05','06','07','08','09','10','11','12'],
		monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		onSelect : function(dateText){
			var dateObj = $(this);
			
		}		
	});
	
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
	
	$("#mainImageUpload").on('change',function(){
		uploadImage(true,-1,$(this));
	});
	
	$("#subImageUpload0").on('change',function(){
		uploadImage(false,0,$(this));
	});
	
	$("#subImageUpload1").on('change',function(){
		uploadImage(false,1,$(this));
	});
	
	$("#subImageUpload2").on('change',function(){
		uploadImage(false,2,$(this));
	});
	
	$("#subImageUpload3").on('change',function(){
		uploadImage(false,3,$(this));
	});
	
	$("#subImageUpload4").on('change',function(){
		uploadImage(false,4,$(this));
	});
	
	$("#subImageUpload5").on('change',function(){
		uploadImage(false,5,$(this));
	});
	
	$("#subImageUpload6").on('change',function(){
		uploadImage(false,6,$(this));
	});
	
	$("#subImageUpload7").on('change',function(){
		uploadImage(false,7,$(this));
	});
	
	$("#subImageUpload8").on('change',function(){
		uploadImage(false,8,$(this));
	});
	
	$("#htmlDesc").tinymce({
		script_url : _requestPath + '/resources/script/jquery/tinymce3/tiny_mce.js',
		theme : "advanced",
		language : 'ko',
		height : '900',
		width : '900',
		theme_advanced_path: false,  
		
		forced_root_block: false,  
		theme_advanced_toolbar_location : "top",
		plugins: "style,layer,table,save,advhr,advimage," +
				"advlink,media,searchreplace,contextmenu," +
				"paste,nonbreaking,xhtmlxtras,autosave"
		
	});
	
});


uploadImage = function(isMain,idx,$obj){
	
	$("#uploadNumber").val(idx);
	
	var url = "/admin/uploadImage";
	var imagePathId = null;
	var thumbnailPathId = null;
	
	var showBtnId = null;
	if(isMain){
		imagePathId = "#mainImagePath";
		thumbnailPathId = "#mainThumbnailPath";
		url = url + "/main"
		showBtnId = "#mainShowBtn";
	}else{
		imagePathId = "#subImagePath" + idx;
		thumbnailPathId = "#subThumbnailPath" + idx;
		url = url + "/sub/"+idx
		showBtnId = "#subShowBtn" + idx;
		$("#subImageUpload" + idx).attr('name', 'subImageUpload');
	}
	
	var selectedVal = $obj.val();
	if(selectedVal == null || selectedVal.length == 0){
		$("#imageOriginal").html('');
		$("#imageThumbnail").html('');
		
		$(imagePathId).attr("value","");
		$(thumbnailPathId).attr("value","");
	
		$(showBtnId).hide();
		
		return;
	}
	$("#imageOriginal").html("<img src='" + _requestPath + "/resources/images/loading.gif'/>");
	$("#imageThumbnail").html("<img src='" + _requestPath + "/resources/images/loading.gif'/>");
	$("#productForm").ajaxSubmit({
		url : _requestPath + url,
		dataType : 'json',
		success : function(response,statusText){
			var success = response.success;
			if(success == true){
				var imagePath = response.imagePath;
				var thumbnailPath = response.thumbnailPath;
				
				$(imagePathId).attr("value",imagePath);
				$(thumbnailPathId).attr("value",thumbnailPath);
				
				$("#imageOriginal").html("<img src='" + imagePath + "'/>");
				$("#imageThumbnail").html("<img src='" + thumbnailPath + "' width=200/>");
				
				$(showBtnId).show();
			}else{
				$("#imageOriginal").html('');
				$("#imageThumbnail").html('');
				
				var msg = response.msg;
				alert(msg);
			}
		},
		error : function(){
			$("#imageOriginal").html('');
			$("#imageThumbnail").html('');
			
			$(imagePathId).attr("value","");
			$(thumbnailPathId).attr("value","");
			
			$(showBtnId).hide();
			alert("Error");
		}
	});	
};


showImage = function(isMain,idx){
	var imagePath = null;
	var thumbnailPath = null;
	
	if(isMain){
		imagePath = $("#mainImagePath").val();
		thumbnailPath = $("#mainThumbnailPath").val();
	}else{
		imagePath = $("#subImagePath" + idx).val();
		thumbnailPath = $("#subThumbnailPath" + idx).val();
	}

	$("#imageOriginal").html("<img src='" + imagePath + "'/>");
	
	
};

doSubmit = function(){
	var category = $("#category option:selected").val();
	
	if(category.length == 0){
		alert("카테고리를 하나이상 선택하세요.");
		return;
	}
	
	var feature = $("#feature option:selected").val();
	if(feature.length == 0){
		alert("상품성분을 하나이상 선택하세요.");
		return;
	}
	
	
	var name = $("#name").val();
	if(name.length == 0){
		alert("상품명을 입력하세요.");
		return;
	}
	
	var productCode = $("#productCode").val();
	if(productCode.length == 0){
		alert("상품코드를 입력하세요.");
		return;
	}
	
	var mainImagePath = $("#mainImagePath").val();
	if(mainImagePath.length == 0){
		alert("대표 이미지를 업로드하세요.");
		return;
	}
	
	var htmlDescription = $("#htmlDesc").val();
	if(htmlDescription.length == 0){
		alert("상품상세정보를 입력하세요.");
		return;
	}
	
	
	
	var productPrice = $("#productPrice").val();
	if(productPrice.length == 0){
		alert("상품가를 입력하세요.");
		return;
	}
	
	var miloPrice = $("#miloPrice").val();
	if(miloPrice.length == 0){
		alert("마일로가를 입력하세요.");
		return;
	}
	
	var totalCount = $("#totalCount").val();
	if(totalCount.length == 0){
		alert("등록수량을 입력하세요.");
		return;
	}

	$("#productForm").attr({action : _requestPath + "/admin/saveProduct"}).submit();
	$("#btnSaveInfo").attr("disabled","disabled");
};

