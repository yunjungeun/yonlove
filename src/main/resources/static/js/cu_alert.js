//html 본문에서 옵션 값을 받아와 저장하는 변수
var fkid = selectName;
function mappingurl(submiturl,sucessurl, int, text, cnt){
    document.getElementById("alert").addEventListener("submit", function (event){
    event.preventDefault();

    var data = {};

        for(var i = 1; i <= int; i++){
            var name = ".field"+i;
            $(name).each(function() {
             var field = $(this).attr("name");
             data[field] = $(this).val();
            });
        }

        //숫자형 name값을 처리하는 for문
        for(var j = 1; j<= cnt; j++){
            var intname = ".int"+j;     //int+j 클래스들을 intname 변수에 저장
            $(intname).each(function(){  //각 클래스마다 다음 함수를 반복실행
            var intField = $(this).attr("name");  //intField 변수에 name의 값(dto의 프로퍼티)을 저장
            var parsedValue = parseInt($(this).val());   //사용자가 입력한 값을 숫자로 변경하여 parseValue에 저장
            data[intField] = isNaN(parsedValue) ? 0 : parsedValue;   //parseValue가 null값이면 0으로 수정하여 저장
            });                                                         //.val() 함수는 null값이 들어왔을때 ""형태로 무조건 문자열로 반환, dto의 int=""; 가되서 오류 발생
        }

                // 옵션값이 존재하는 확인하는 if문
    if(fkid != null){
        var selectedValue = $("#fk").val(); // #fk는 select 요소의 ID입니다.
        data[fkid] = selectedValue;
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
