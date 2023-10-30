var fkid = selectName;

function mappingurl(submiturl, successurl, int, text, cnt) {
    document.getElementById("alert").addEventListener("submit", function (event) {
        event.preventDefault();

        var data = new FormData(document.getElementById("alert")); // FormData 객체 생성

        for (var i = 1; i <= int; i++) {
            var name = ".field" + i;
            $(name).each(function () {
                var field = $(this).attr("name");
                data.append(field, $(this).val()); // 데이터를 FormData에 추가
            });
        }

        for (var j = 1; j <= cnt; j++) {
            var intname = ".int" + j;
            $(intname).each(function () {
                var intField = $(this).attr("name");
                var parsedValue = parseInt($(this).val());
                data.append(intField, isNaN(parsedValue) ? 0 : parsedValue);
            });
        }
        if (fkid != null) {
            var selectedValue = $("#fk").val();
            data.append(fkid, selectedValue);
        }

       if (test != null) {
           var selectedValue = $("#fk").val();
           data.append(test, selectedValue);

       }


        $.ajax({
            url: submiturl,
            type: "POST", // HTTP 메서드명 수정
            data: data,    // FormData를 전송 데이터로 사용
            processData: false,
            contentType: false,
            cache: false,
            enctype: 'multipart/form-data',
            success: function (response) {
                alert("정상적으로 " + text + " 되었습니다");
                window.location.href = successurl;
            },
            error: function (error) {
                alert(text + " 중 오류가 발생 했습니다");
                window.location.href = successurl;
            }
        });
    });
}

