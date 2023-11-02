function dateButton(buttonId){
    switch (buttonId){
        case 'preYear' :  var url ='/calendar/preyear'; break;
        case 'preMonth' :  var url ='/calendar/premonth'; break;
        case 'nextMonth' :  var url ='/calendar/nextmonth'; break;
        case 'nextYear' :  var url ='/calendar/nextyear'; break;
    }

      var year = document.getElementById('year').textContent;
      var month = document.getElementById('month').textContent;

    $.ajax({
        url : url,
        type : 'POST',
        data : {"year" : year, "month" : month},
        success : function(response){
            // 서버에서 받은 JSON 데이터를 사용하여 Mustache 템플릿 렌더링
            var template = "<tr><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>"
                            + "</tr>{{#calendar}}<tr>{{#.}}<td>{{.}}</td>{{/.}}</tr>{{/calendar}}";
            var rendered = Mustache.render(template, response);
            document.getElementById('calendar-container').innerHTML = rendered;

            var dateTemplate = '<h1><span id="year">{{year}}</span>년 <span id="month">{{month}}</span>월</h1>';
            var dateRendered = Mustache.render(dateTemplate, response);
            document.getElementById('currentDate').innerHTML = dateRendered;
        },
             error: function(error) {
                 alert("오류 발생: " + error);
             }
    });
};
