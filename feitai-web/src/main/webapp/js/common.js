function issueBackStage(url,type,formId,sfn,efn){
	$.ajax({
		type : 'POST',
		url : 'loginCheck.html',
		data : $("#_form").serialize(),
		success : function(data) {
			var json = eval(data); //数组         
			$.each(json, function(index, item) {
				var errorCode = json[index].errorCode;
				var errorMessage = json[index].errorMessage;
				alert(errorCode);
				if(errorCode){
					throwErrorAtEEE(errorMessage,"EEE")
				}
			});
			sfn();
		},
		error : efn()
	});
}

function throwErrorAtEEE(error,spanId){
	$('#'+spanId).html(error);
	alert($('#'+spanId).html());
}