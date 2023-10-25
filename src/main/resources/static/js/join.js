$(function() {

    $(".joinForm").submit(function(e){
        e.preventDefault();
        var formData = new FormData($(this)[0]);
        for (let value of formData.values()) {
            if (value.trim() === ''){
                alert('모든 항목을 입력해주세요.');
                return;
            }
        }

        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if(!emailRegex.test(formData.get('email'))) {
            alert('이메일을 다시 확인해주세요.');
            return;
        }

        var data = {

            user_id: formData.get('user_id'),
            pw: formData.get('pw'),

        };

        $.ajax({
            url: 'login/signup',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (data, txtStatus, xhr) {
                var code = xhr.status;
                if(200 <= code && code < 300)
                {
                    if(data){
                        $(location).attr('href', '/cs/qna');
                    } else {
                        alert("이미 아이디가 존재합니다.");
                    }
                }
                else {
                    alert("회원가입 실패");
                }
            }
        });
        return false;
    });

});