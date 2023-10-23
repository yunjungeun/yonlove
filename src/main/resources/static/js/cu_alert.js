
function mappingurl(submiturl,sucessurl, int, text){
    document.getElementById("alert").addEventListener("submit", function (event){
    event.preventDefault();

    var data = {};

        for(var i = 1; i <= int; i++){
            var name = ".field"+i;  // name <-  ". field" +i  클래스이름 대체 +1 갯수숫자의미  .점은 선택자 클래스라서 점으로 표시
            $(name).each(function() {    // $(클랙스이름) 넣고 반복해서 함수를
             var field = $(this).attr("name");   // 클래스 필드=  뷰페이지의 네임 값을 넣음
             data[field] = $(this).val();  // 데이터안에 값을 넣음
            });
        }

       $.ajax({
            url: submiturl,
            type : "GET",
            data : data,
            success:function(response){
                alert("정상적으로 " +text+" 되었습니다");
                window.location.href = sucessurl ;
            },
            error: function(error){
                alert(text+" 중 오류가 발생 했습니다");
                window.location.href = sucessurl ;

            }
       });
    });
}
