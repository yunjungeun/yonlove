$(function() {

	$("#loginForm").submit(function(e){
		e.preventDefault();
		var formData = new FormData($(this)[0]);
		for (let value of formData.values()) {
			if (value.trim() === ''){
				alert('이메일 또는 비밀번호를 입력해주세요.');
				return;
			}
		}

		var autoLogin = false;
		if(formData.get('auto') === 'on') {
			autoLogin = true;
		}

		var data = {
            email: formData.get('email'),
            password: formData.get('password'),
            autoLogin
        };

		$.ajax({
			url: '/login',
			type: 'POST',
			data: JSON.stringify(data),
			dataType: 'json',
			contentType: 'application/json',
			success: function (data, txtStatus, xhr) {
				var code = xhr.status;
				if(200 <= code && code < 300)
				{
					if(data){
						$(location).attr('href', '/bbs');
					} else {
						alert("로그인 실패");
					}
				}
				else {
					alert("로그인 실패");
				}
			}
		});
		return false;
	});

});