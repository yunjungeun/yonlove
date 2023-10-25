//이 js는 프로젝트 게시글 작성 중 기획 시작일과 기획 (예상)종료일을 공란으로 작성했을때
//default값으로 날짜를 알아서 넣어주는 js임.

// 기획 시작일을 공백으로 했을시 작동되는 함수
function getStartDefaultDate() {
    var today = new Date();  //오늘 날짜와 시간값  --> Mon Oct 25 2023 14:36:47 GMT+0000  이런식으로 나옴
    var year = today.getFullYear();  // today 값에서 현재년도를 가저오는 메소드

    // js에서는 0월부터 시작됨, 그래서 1을 더해줘야함. 이 값을 스트링으로 바꿈// padStart는 월을 2자리수로 바꾸고 앞1월이면 01월로 바꿈
    var month = (today.getMonth() + 1).toString().padStart(2, '0');
    //오늘 일자를 today.getDate()로 가져와 문자열로 변환하고, 1자리 수면 02일 식으로 padStart가 바꿈
    var day = today.getDate().toString().padStart(2, '0'); //
    var formattedDate = year + '-' + month + '-' + day;  //프로퍼티에 저장할 수있게 스트링으로 바꿈
    return formattedDate;  //저장된 문자열 변수를 반환
}


// 기획 (예상)종료일 공백으로 했을시 작동되는 함수는 기획 시작일 +1년 으로 설정
function getEndDefaultDate() {
    var today = new Date();
    var year = today.getFullYear()+1;  // 1을 더해 내년 값을 저장
    var month = (today.getMonth() + 1).toString().padStart(2, '0');
    var day = today.getDate().toString().padStart(2, '0'); //
    var formattedDate = year + '-' + month + '-' + day;
    return formattedDate;
}

// 오늘 날짜를 가져와서 input 요소의 value 속성에 할당
document.getElementById('default_start').value = getStartDefaultDate();
document.getElementById('default_end').value = getEndDefaultDate();