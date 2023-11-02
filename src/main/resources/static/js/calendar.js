function preMonth(){
      var year = document.getElementById('year').value;
      var month = document.getElementById('month').value;

    $.ajax({
        url : '/calendar/premonth',
        type : 'POST',
        data : {"year" : year, "month" : month},
        success : function(response){
            alert("가져왓나");
            // 서버에서 받은 JSON 데이터를 사용하여 Mustache 템플릿 렌더링
            var template = document.getElementById('calendar-template').innerHTML;
            var rendered = Mustache.render(template, response);
            document.getElementById('calendar-container').innerHTML = rendered;
        },
             error: function(error) {
                 alert("오류 발생: " + error);

             }
    });
};

