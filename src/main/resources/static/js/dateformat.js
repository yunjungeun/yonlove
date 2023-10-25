//머스테치에서 dates{} 로 저장된 키값만 들어가게됨.
//keys = {date1, date2, date3} 키값만 들어있음
var keys = Object.keys(dates);

for(var i =0; i<keys.length; i++){
var key = keys[i]

//dates[date1]의 값이 stringDate에 들어가게됨
var stringDate = dates[key];

//문자열로된 시간값을 정규식 '/g'로 2023-12 부분의 하이픈을 '/'이걸로 바꿔줌
//바꿔주는 이유는 특정브라우저에서는 '/'표시되기 때문임
var formatDate = new Date(stringDate.replace(/-/g, '/'));

//date형태로 바뀐 값은 2023/11/11 12:00 분처럼 아직 시간값이 그대로 있음
//toISOSTING().split로 쪼개줘야함. 'T'는 시간부분을 쪼갠다는 뜻이고 [0]는 쪼개진부분의 배열중 첫번째 즉, 날짜값만 가져오라는 뜻
var formattedDate = formatDate.toISOString().split('T')[0];
//23/12/12 이렇게 된 값을 format1 에 넣어줌
// (i+1)이 안들어가면 fomart0 이라는 id가 생기는데, 머스테치에서는 id값을 format1 부터 시작하게 했기 때문에 i에서 1을 더해저야함
document.getElementById('format'+(i+1)).value = formattedDate;
}
