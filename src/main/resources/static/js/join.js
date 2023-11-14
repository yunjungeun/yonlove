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
        		type : 'POST',
        		data : id,
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
    	}   // End function checkIdDuplicate()

    $("#check_button").on("click", function() {
                checkIdDuplicate();
            });  // End
        });    // End   $(document).ready(function()

        function passwordCheckFunction() {
        		var userPassword1 = $('#pw').val();
        		var userPassword2 = $('#confirm_password').val();

        		if(userPassword1 != userPassword2) {
        			$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
        		} else {
        			$('#passwordCheckMessage').html('');	//일치하면 메시지가 사라지게함!!
        		}
        	}

