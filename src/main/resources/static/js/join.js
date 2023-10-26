    $(document).ready(function() {
    	function checkIdDuplicate() {


    	/*usercomfim*/
    	//ID 중복 확인
    	//id를 입력할 수 있는 input text 영역을 벗어나면 동작한다.
    	/*$("#user_id").on("click", checkIdDuplicate() {*/

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
