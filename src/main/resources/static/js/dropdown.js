/*사용법
최상위 옵션값부터 select 태그 id를 fk1 부터 지정해서 fk3까지 지정
실제 서버로 넘어가는 값은 클래스 'field'로 지정할것

만약 project 옵션이 최상위라면 project id를 fk1, 시나리오에 fk2을 지정*/

/*js변수에 저장된 머스테치 변수를 불러옴*/
var parsedJson = JSON.parse(jsonValue);
var dropdown = document.getElementById("fk1");


//드롭다운 url을 만들어내기 위한 변수와 url생성

//fk2 라는 id가 존재하지 않는경우도 있는데, if문이 없다면 null값으로 인해 다음라인이 실행되지 않음. if문이 있어야함.
var subDropdown = document.getElementById("fk2");
    if(subDropdown !== null){
        var dropdownFk2Name = subDropdown.getAttribute('name');
        var subUrl = "/dropdown/" + dropdownFk2Name;
    }
var dropdownFk1Name = dropdown.getAttribute('name');

var mainUrl = "/dropdown/" + dropdownFk1Name;
//url 생성구분 end

for(var key in parsedJson){
    var option = document.createElement("option");
    option.value = key;
    option.text = parsedJson[key];
    dropdown.appendChild(option);
}


$(document).ready(function () {
    // 'fk1'의 선택이 변경될 때마다 이벤트 핸들러 실행
    $('#fk1').change(function () {
        var selectedFkValue = $(this).val();

        // AJAX 요청 보내기
        $.ajax({
            type: 'POST', // 요청 타입
            url: mainUrl, // 서버의 엔드포인트 URL
            data: { pkid : selectedFkValue }, // 보낼 데이터
            dataType: 'json', // 응답 데이터 타입
            success: function (response) {
            //response 은 {pk:name} 으로 구성된 json문자열  여기서 pk는 프로퍼티=속성=key고, name 값 = value
                var subSelect = $('#fk2');
                subSelect.empty(); // 기존 옵션 제거


                for (var key in response) {
                            if (response.hasOwnProperty(key)) {  //만약 프로퍼티(속성)가 존재한다면 옵션값을 생성
                                var value = response[key];  //response[key]는 프로퍼티의 값을 의미함
                                // key 값을 옵션의 value로, value 값을 옵션의 텍스트로 사용하여 옵션 엘리먼트를 생성
                                var option = '<option value="' + key + '">' + value + '</option>';
                                subSelect.append(option);
                            } else{
                                var option = '<option>없음</option>';
                                subSelect.append(option);
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
        var selectedFkValue = $(this).val();

        // AJAX 요청 보내기
        $.ajax({
            type: 'POST', // 요청 타입
            url: subUrl, // 서버의 엔드포인트 URL
            data: { pkid : selectedFkValue }, // 보낼 데이터
            dataType: 'json', // 응답 데이터 타입
            success: function (response) {

            //response 은 {pk:name} 으로 구성된 json문자열  여기서 pk는 프로퍼티=속성=key고, name 값 = value
                var subSelect = $('#fk3');
                subSelect.empty(); // 기존 옵션 제거

                    for (var key in response) {
                            if (response.hasOwnProperty(key)) {  //만약 프로퍼티(속성)가 존재한다면 옵션값을 생성
                                var value = response[key];  //response[key]는 프로퍼티의 값을 의미함
                                // key 값을 옵션의 value로, value 값을 옵션의 텍스트로 사용하여 옵션 엘리먼트를 생성
                                var option = '<option value="' + key + '">' + value + '</option>';
                                subSelect.append(option);
                            }
                        }
            },
            error: function (xhr, status, error) {
                console.error('AJAX 요청 실패: ' + status + ', ' + error);
            }
        });
    });
});