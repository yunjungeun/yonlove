
function mappingurl(submiturl,sucessurl, int){
    document.getElementById("alert").addEventListener("submit", function (event){
    event.preventDefault();

    var data = {};

        for(var i = 1; i <= int; i++){
            var name = ".insert"+i;
            $(name).each(function() {
             var field = $(this).attr("name");
             data[field] = $(this).val();
            });
        }

       $.ajax({
            url: submiturl,
            type : "GET",
            data : data,
            success:function(response){
                alert("정상적으로 등록 되었습니다");
                window.location.href = sucessurl ;
            },
            error: function(error){
                alert("작성 중 오류가 발생 했습니다");
                window.location.href = sucessurl ;

            }
       });
    });
}
