//html 본문에서 옵션 값을 받아와 저장하는 변수
var fkid = selectName;
try{var produce = prodcueId;}catch (error){}
try{
var radioName = radioButtonName;
}catch (error){}

function mappingurl(submiturl, int, text, cnt){
    document.getElementById("alert").addEventListener("submit", function (event){
    event.preventDefault();

    var data = {};


     /*   var fileData = new FormData(document.getElementById("alert"));
        data.append("file", fileData);*/

        for(var i = 1; i <= int; i++){
            var name = ".field"+i;  // name <-  ". field" +i  클래스이름 대체 +1 갯수숫자의미  .점은 선택자 클래스라서 점으로 표시
            $(name).each(function() {    // $(클랙스이름) 넣고 반복해서 함수를
             var field = $(this).attr("name");   // 클래스 필드=  뷰페이지의 네임 값을 넣음
             data[field] = $(this).val();  // 데이터안에 값을 넣음
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
        var selectedValueFk3 = $("#fk3").val(); // #fk는 select 요소의 ID입니다.
        if(!selectedValueFk3){
            var selectedValueFk2 = $("#fk2").val();
            if(!selectedValueFk2){
                var selectedValueFk1 = $("#fk1").val();
                data[fkid] = selectedValueFk1;
            }else{
                data[fkid] = selectedValueFk2;
            }
        }else{
        data[fkid] = selectedValueFk3;
        }
    }

    if(produce != null){
        var produceSelect = $("#fk4").val();
        data[produce] = produceSelect;
    }

          //<!--라디오 버튼의 값을 가져오기 위해 라디오 버튼 요소들을 선택합니다.-->
        var selectedRadio = null;
        if(radioName != null){
           var radioButtons = document.querySelectorAll('.radio');
           selectedRadio = document.querySelector('input[name='+radioName+']:checked').value;
           data[radioName] = selectedRadio;
        } else{}

    $.ajax({
            url: submiturl,
            type : "GET",
            data : data,
            success:function(response){
                alert("정상적으로 " +text+" 되었습니다");

                //업로드 js페이지를 안달아놨을경우 건너뜀
                if (typeof uploadFile === 'function'){
                    //업로드관련된 함수, uploadFile.js참조
                    uploadFile();
                    updateFile();
                }

                window.location.href = response ;
            },
            error: function(error){
                alert(text+" 중 오류가 발생 했습니다");
                window.location.href = response ;

            }
       });
    });
}
