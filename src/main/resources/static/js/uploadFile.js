  function uploadFile() {
            var fileInput = document.getElementById('fileInput');
            var file = fileInput.files[0];

            if (file) {
                var formData = new FormData();
                formData.append('file', file);

         $.ajax({
           url: '/upload',  // 서버로 업로드를 처리하는 API endpoint URL
           type: 'POST',
           data: formData,
           processData: false,  // 데이터를 처리하지 않도록 설정
           contentType: false,  // 컨텐츠 타입을 설정하지 않도록 설정
           success: function(data) {
              alert("파일 업로드 성공");
           },
           error: function(error) {
              console.error('파일 업로드 실패');
           }
         });

           }
       }