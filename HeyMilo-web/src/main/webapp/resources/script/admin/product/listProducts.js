var searchTable;

$(document).ready(function(){
	
	/**
	 * 각종 버튼의 click이벤트 처리.
	 */
	$("#btnSearch").click(function(){
		doSearch();
	});
	
	var url = _requestPath + "/admin/searchProducts";
	
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
		       { data : "id", sortable : false},
		       { data : "mainImagePath", width : "15%"},
		       { data : "name"},
		       { data : "newProduct", width : "10%"},
		       { data : "canSubscription", width : "10%"},
		       { data : "miloPrice", width : "10%"},
		       { data : "productPrice", width : "10%"},
		       { data : "status", width : "10%"},
		       { data : "updated",width:"10%"}
		   ],
		   processing: true,
		   serverSide : true,
		   columnDefs : [
				{
				    targets : 1,
				    render : function(data,type,row){
				    	return "<img src=\"" + data + "\" width='100'/>";
				    }
				},
		       {
		            targets : 2,
		            render : function(data,type,row){
		            	return "<a href='javascript:viewProduct(\"" + row.id + "\");'>" + data + "</a>";
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
	var keyword = $("#searchKeyword").val();
	if(keyword.length > 0){
		params.keyword = keyword;
	}else{
		params.keyword = "";
	}
	var status = $(":radio[name='status']:checked").val();
	params.status = status;
	
	if($("#newProduct").is(":checked")){
		params.newProduct = true;
	}
	
	if($("#canSubscription").is(":checked")){
		params.canSubscription = true;
	}
	
	return params;
};


doSearch = function(){
	searchTable.ajax.reload(function(json){
		
	});
};

addProduct = function(){
	document.location.href = _requestPath + "/admin/productForm";
};

viewProduct= function(id){
	document.location.href = _requestPath + "/admin/productForm?id=" + id;
};
