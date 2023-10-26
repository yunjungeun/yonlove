    $(document).ready(function() {
    	function checkIdDuplicate() {

    		var id = $("#user_id").val();
    		if(id == '' || id.length == 0) {
    			alert("공백은 ID로 사용할 수 없습니다.");
    			return;
    			}

        	//Ajax로 전송
        	$.ajax({
        		url : '/ConfirmId',
        		data : {
        			user_id : id
        		},
        		type : 'POST',
        		dataType : 'json',
        		success : function(result) {
        			if (result === false) {
        				alert("사용가능 ID 입니다.");
        			} else{
        				alert("불가능 ID 입니다.");
        				$("#user_id").val('');
        			}
        		},
        		    error: function(error) {
                        alert("못보냄");
                    }
        	}); //End Ajax
    	}
    $("#check_button").on("click", function() {
                checkIdDuplicate();
            });
        });
