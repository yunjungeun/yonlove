  function uploadFile() {
       var fileInput = document.getElementById('fileInput');
        //fileInput 가 비어있으면 로직실행안함
       if(fileInput){
            var file = fileInput.files[0];
            var tableId= document.getElementById('tableId').value;

            if (file) {
                var formData = new FormData();
                formData.append('file', file);
                formData.append('table', tableId);

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
       }

  function updateFile() {
        var fileInput = document.getElementById('updateFile');

         //fileInput이 비어있으면 로직실행안함
        if(fileInput){
        //게시글에 있는 히든값
           var fkValue = document.getElementById('pk').value;
           var file = fileInput.files[0];

            if (file) {
                var formData = new FormData();
                formData.append('newfile', file);
                formData.append('fk', fkValue);

                 $.ajax({
                   url: '/updateFile',  // 서버로 업로드를 처리하는 API endpoint URL
                   type: 'POST',
                   data: formData,
                   processData: false,  // 데이터를 처리하지 않도록 설정
                   contentType: false,  // 컨텐츠 타입을 설정하지 않도록 설정
                   success: function(data) {
                      alert("파일을 수정했습니다");
                   },
                   error: function(error) {
                      console.error('파일 수정 실패');
                   }
               });

            }
           }
       }

function deleteFile() {
    //게시글에 있는 히든값 pk 값
   var fkValue = document.getElementById('pk').value;
    if(fkValue){
            $.ajax({
            url: '/deleteFile',
            type : 'POST',
            data : {fk : fkValue},
            success : function(respone){
                alert("파일삭제 완료")
            },
            error : function(respone){
                 console.error('파일삭제 실패');
            }
        });
    }
}