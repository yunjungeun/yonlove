function preMonth(){
      var year = document.getElementById('year').value;
      var month = document.getElementById('month').value;

      alert("현재 년 / 월 은 " + year + "/" + month);

    $.ajax({
        url : '/calendar?year='+year+'&month='+month,
        type : 'POST',
        data : {"year" : year, "month" : month},
        success : function(respone){
            alert("가져왓나")

        }

    });
}
