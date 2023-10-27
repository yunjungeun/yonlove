function mappingurl(submiturl, sucessurl, int, text, cnt) {
    document.getElementById("alert").addEventListener("submit", function (event) {
        event.preventDefault();

        var formData = new FormData(document.getElementById("alert"));

        for (var i = 1; i <= int; i++) {
            var name = ".field" + i;
            $(name).each(function () {
                var field = $(this).attr("name");
                formData.append(field, $(this).val());
            });
        }

        for (var j = 1; j <= cnt; j++) {
            var intname = ".int" + j;
            $(intname).each(function () {
                var intField = $(this).attr("name");
                var parsedValue = parseInt($(this).val());
                formData.append(intField, isNaN(parsedValue) ? 0 : parsedValue);
            });
        }

        if (fkid != null) {
            var selectedValue = $("#fk").val();
            formData.append(fkid, selectedValue);
        }

        $.ajax({
            url: submiturl,
            type: "POST", // POST 메소드를 사용하여 파일 업로드와 문자열 데이터 전송
            data: formData,
            processData: false, // 데이터 처리를 jQuery에 위임하지 않음
            contentType: false, // 컨텐츠 타입을 false로 설정하여 jQuery가 자동으로 설정하지 않도록 함
            cache: false,
            enctype: 'multipart/form-data',
            success: function (response) {
                alert("정상적으로 " + text + " 되었습니다");
                window.location.href = sucessurl;
            },
            error: function (error) {
                alert(text + " 중 오류가 발생 했습니다");
                window.location.href = sucessurl;
            }
        });
    });
}
