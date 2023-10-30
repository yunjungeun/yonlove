/*js변수에 저장된 머스테치 변수를 불러옴*/
var parsedJson = JSON.parse(jsonValue);
var dropdown = document.getElementById("fk1");


for(var key in parsedJson){
    var option = document.createElement("option");
    option.value = key;
    option.text = parsedJson[key];
    dropdown.appendChild(option);
}


$(document).ready(function () {
    // 'fk3'의 선택이 변경될 때마다 이벤트 핸들러 실행
    $('#fk1').change(function () {
        var selectedFkValue = $(this).val();

        // AJAX 요청 보내기
        $.ajax({
            type: 'POST', // 요청 타입
            url: '/fk3', // 서버의 엔드포인트 URL
            data: { pkid : selectedFkValue }, // 보낼 데이터
            dataType: 'json', // 응답 데이터 타입
            success: function (response) {
            //response 은 {pk:name} 으로 구성된 json문자열  여기서 pk는 프로퍼티=속성=key고, name 값 = value
                var fk2Select = $('#fk2');
                fk2Select.empty(); // 기존 옵션 제거

                for (var key in response) {
                            if (response.hasOwnProperty(key)) {  //만약 프로퍼티(속성)가 존재한다면 옵션값을 생성
                                var value = response[key];  //response[key]는 프로퍼티의 값을 의미함
                                // key 값을 옵션의 value로, value 값을 옵션의 텍스트로 사용하여 옵션 엘리먼트를 생성
                                var option = '<option value="' + key + '">' + value + '</option>';
                                fk2Select.append(option);
                            } else{
                                var option = '<option>없음</option>';
                                fk2Select.append(option);
                            }
                        }
            },
            error: function (xhr, status, error) {
                console.error('AJAX 요청 실패: ' + status + ', ' + error);
            }
        });
    });
});

$(document).ready(function () {
    // 'fk2'의 선택이 변경될 때마다 이벤트 핸들러 실행
    $('#fk2').change(function () {
        var selectedFk2Value = $(this).val();

        // AJAX 요청 보내기
        $.ajax({
            type: 'POST', // 요청 타입
            url: '/fk2', // 서버의 엔드포인트 URL
            data: { pkid : selectedFk2Value }, // 보낼 데이터
            dataType: 'json', // 응답 데이터 타입
            success: function (response) {
            //response 은 {pk:name} 으로 구성된 json문자열  여기서 pk는 프로퍼티=속성=key고, name 값 = value
                var fk2Select = $('#fk3');
                fk2Select.empty(); // 기존 옵션 제거


                    for (var key in response) {
                            if (response.hasOwnProperty(key)) {  //만약 프로퍼티(속성)가 존재한다면 옵션값을 생성
                                var value = response[key];  //response[key]는 프로퍼티의 값을 의미함
                                // key 값을 옵션의 value로, value 값을 옵션의 텍스트로 사용하여 옵션 엘리먼트를 생성
                                var option = '<option value="' + key + '">' + value + '</option>';
                                fk2Select.append(option);
                            }
                        }
            },
            error: function (xhr, status, error) {
                console.error('AJAX 요청 실패: ' + status + ', ' + error);
            }
        });
    });
});