var manufactureMap = {};


$(document).ready(function(){
	
	$(":text").maxlength();
	
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
	
	
});


doSubmit = function(){
	var status = $("#orderStatus option:selected").val();
	
	if(status == "SHIPPING" || status == "COMPLETED") {
		var shippingNo = $("#shippingNo").val();
		if(shippingNo.length == 0){
			alert("운송장번호를 입력하세요.");
			return;
		}
		
		var deliveryCompany = $("#deliveryCompany option:selected").val();
		if(deliveryCompany.length == 0){
			alert("택배사를 선택하세요.");
			return;
		}
	}
	
	$("#orderForm").attr({action : _requestPath + "/admin/orderUpdate"}).submit();
	$("#btnSaveInfo").attr("disabled","disabled");
};

