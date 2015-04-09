var searchTable;

$(document).ready(function(){
	
	var url = _requestPath + "/admin/searchExhibition";
	
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
		       { data : "type", width : "30%"},
		       { data : "created",width:"10%"}
		   ],
		   processing: true,
		   serverSide : true,
		   columnDefs : [
		       {
		            targets : 1,
		            render : function(data,type,row){
		            	return "<a href='javascript:viewExhibition(\"" + row.id + "\");'>" + data + "</a>";
		            }
		       },
		       {
		    	   targets : 3,
		            render : function(data,type,row){
		            	if(data == "NEW_PRODUCT"){
		            		return "신상품기획전";
		            	}else if(data == "SUBSCRIPTION"){
		            		return "정기구독기획전";
		            	}else{
		            		return "기타기획전";
		            	}	
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

addExhibition = function(){
	$("#editModal").on('show.bs.modal',function(){
		initField();
	}).modal('show');
};

viewExhibition= function(id){
	$("#editModal").on('show.bs.modal',function(){
		initField();
		
		var params = {id : id};
		
		$.ajax({
			dataType:  'json', 
			type : 'GET',
			url : _requestPath + "/admin/loadExhibition",
			timeout : 10000,
			data : params,
			beforeSend : function(){
				
			},				
			success : function(result){
				var data = result.data;
				$("#exhibitionId").val(data.id);
				$("#exhibitionName").val(data.name);
				$("#exhibitionDesc").val(data.desc);
				
				if(data.active){
					$("#exhibitionActive").attr("checked", true);
				}else{
					$("#exhibitionActive").attr("checked", false);
				}
				
				$("#exhibitionType").val(data.type);
				
			},
			error : function(response, status, err){
				
				alert("ERROR [" + status + "][" + err + "]");
			}
		});	//Ajax로 호출한다.
		
		
	}).modal('show');
};

initField = function() {
	$("#exhibitionId").val("");
	$("#exhibitionName").val("");
	$("#exhibitionDesc").val("");
};

saveAction = function(){
	var name = $("#exhibitionName").val();
	var desc = $("#exhibitionDesc").val();
	
	if(name.length == 0 || desc.length == 0){
		alert("이름과 설명을 입력하세요");
		return;
	}
	
	var formData = $("#registForm").serialize();
	$.ajax({
		type : "POST",
		url : _requestPath + "/admin/saveExhibition",
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
