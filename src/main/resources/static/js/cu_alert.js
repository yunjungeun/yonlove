//html 본문에서 옵션 값을 받아와 저장하는 변수
var fkid = selectName;
function mappingurl(submiturl, sucessurl, int, text, cnt){
    document.getElementById("alert").addEventListener("submit", function (event){
    event.preventDefault();

    alert("dd")
    console.log("1")
    var data = {};

        for(var i = 1; i <= int; i++){
        console.log("2")
            var name = ".field"+i;
            $(name).each(function() {
             var field = $(this).attr("name");
             data[field] = $(this).val(); // 문자열로 저장

            });
        }
        console.log("3")
        if(cnt!=0){
        console.log("4")
            for(var j= 1; j <= cnt; j++){
                var intcnt = ".int"+j;
                $(intcnt).each(function(){
                var intfield = $(this).attr("name");
                data[intfield] = parseInt(  if($(this).val()!=null){
                                                $(this).val();
                                                }else{"0";}); //숫자형으로 변환
                });
            }
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
