var searchTable;

$(document).ready(function(){
	
	var url = _requestPath + "/admin/searchCategory";
	
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
		       { data : "name"},
		       { data : "desc", width : "40%"},
		       { data : "created",width:"10%"}
		   ],
		   processing: true,
		   serverSide : true,
		   columnDefs : [
		       {
		            targets : 1,
		            render : function(data,type,row){
		            	return "<a href='javascript:viewCategory(\"" + row.id + "\");'>" + data + "</a>";
		            }
		       }
		   ]
	  }).on('preXhr.dt',function(e,settings,data){
		  //Ajax Call을 하기 전에 호출된다.
		  
	  }).on("xhr.dt",function(e,settings,json){
		 $("#totalCount").html(json.recordsTotal);  
	  });
	
});


doSearch = function(){
	searchTable.ajax.reload(function(json){
		
	});
};

addCategory = function(){
	$("#editModal").on('show.bs.modal',function(){
		initField();
	}).modal('show');
};

viewCategory = function(id){
	$("#editModal").on('show.bs.modal',function(){
		initField();
		
		var params = {id : id};
		
		$.ajax({
			dataType:  'json', 
			type : 'GET',
			url : _requestPath + "/admin/loadCategory",
			timeout : 10000,
			data : params,
			beforeSend : function(){
				
			},				
			success : function(result){
				var data = result.data;
				$("#categoryId").val(data.id);
				$("#categoryName").val(data.name);
				$("#categoryDesc").val(data.desc);
				
				if(data.active){
					$("#categoryActive").attr("checked", true);
				}else{
					$("#categoryActive").attr("checked", false);
				}
			},
			error : function(response, status, err){
				
				alert("ERROR [" + status + "][" + err + "]");
			}
		});	//Ajax로 호출한다.
		
		
	}).modal('show');
};

initField = function() {
	$("#categoryId").val("");
	$("#categoryName").val("");
	$("#categoryDesc").val("");
};

saveAction = function(){
	var name = $("#categoryName").val();
	var desc = $("#categoryDesc").val();
	
	if(name.length == 0 || desc.length == 0){
		alert("카테고리 이름과 설명을 입력하세요");
		return;
	}
	
	var formData = $("#registForm").serialize();
	$.ajax({
		type : "POST",
		url : _requestPath + "/admin/saveCategory",
		data : formData,
		success : function(json,status) {
			var success = json.success;
			if(success){
				alert("성공적으로 저장되었습니다.");
				$("#editModal").modal('hide');
				doSearch();
			}else{
				alert("저장중에 에러가 발생하였습니다.");
			}
		},
		error : function(data,status) {
			alert("Error");
		}
	});
};
