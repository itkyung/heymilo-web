var searchTable;

$(document).ready(function(){
	
	/**
	 * 각종 버튼의 click이벤트 처리.
	 */
	$("#btnSearch").click(function(){
		doSearch();
	});
	
	var url = _requestPath + "/admin/searchOrders";
	
	searchTable = $('#searchResult').DataTable({
		  language: {
			  paginate: {
				  next: "다음",previous : "이전"
			  }
		   },
		   scrollY: 400,
		   searching: false,
		   paging:true,
		   displayLength : 10,
	       lengthChange : true,
	       info : false,
	       dom : '<"top"i>rt<"bottom"lp><"clear">',
		   ajax : {
			   url : url,
			   type : "POST"
		   },
		   columns : [
		     
		       { data : "orderNo", width : "10%"},
		       { data : "status", width : "10%"},
		       { data : "buyerName", width : "10%"},
		       { data : "productDesc"},
		       { data : "totalPrice", width : "10%"},
		       { data : "createdStr", width : "10%"},
		       { data : "updatedStr", width : "10%"}
		   ],
		   processing: true,
		   serverSide : true,
		   columnDefs : [
		       {
		            targets : 0,
		            render : function(data,type,row){
		            	return "<a href='javascript:viewOrder(\"" + row.id + "\");'>" + data + "</a>";
		            }
		       },
		       {
		            targets : 4,
		            render : function(data,type,row){
		            	return data + "원";
		            }
		       }
		   ]
	  }).on('preXhr.dt',function(e,settings,data){
		  //Ajax Call을 하기 전에 호출된다.
		  makeParams(data);
	  }).on("xhr.dt",function(e,settings,json){
		 $("#totalCount").html(json.recordsTotal);  
	  });
	
});


/**
 * 검색조건을 만든다.
 */
makeParams = function(params){
	var orderNo = $("#orderNo").val();
	if(orderNo.length > 0){
		params.orderNo = orderNo;
	}else{
		params.orderNo = "";
	}
	var status = $(":radio[name='status']:checked").val();
	params.status = status;

	var buyerId = $("#buyerId").val();
	if(buyerId.length > 0){
		params.buyerId = buyerId;
	}
	
	return params;
};


doSearch = function(){
	searchTable.ajax.reload(function(json){
		
	});
};


viewOrder= function(id){
	document.location.href = _requestPath + "/admin/orderForm?id=" + id;
};
