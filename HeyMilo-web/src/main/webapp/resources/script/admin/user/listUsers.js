var searchTable;

$(document).ready(function(){
	
	/**
	 * 각종 버튼의 click이벤트 처리.
	 */
	$("#btnSearch").click(function(){
		doSearch();
	});
	
	var url = _requestPath + "/admin/searchUsers";
	
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
		     
		       { data : "name"},
		       { data : "loginId", width : "15%"},
		       { data : "status", width : "10%"},
		       { data : "createdAtStr", width : "15%"},
		       { data : "lastLoginDateStr", width : "15%"}
		   ],
		   processing: true,
		   serverSide : true,
		   columnDefs : [
		       {
		            targets : 0,
		            render : function(data,type,row){
		            	return data;
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
	var keyword = $("#keyword").val();
	if(keyword.length > 0){
		params.keyword = keyword;
	}else{
		params.keyword = "";
	}
	var status = $(":radio[name='status']:checked").val();
	params.status = status;
	
	return params;
};


doSearch = function(){
	searchTable.ajax.reload(function(json){
		
	});
};


viewOrder= function(id){
	document.location.href = _requestPath + "/admin/orderForm?id=" + id;
};
