
function mappingurl(submiturl,sucessurl, int, text){
    document.getElementById("alert").addEventListener("submit", function (event){
    event.preventDefault();

    var data = {};

        for(var i = 1; i <= int; i++){
            var name = ".field"+i;
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
                alert("정상적으로 " +text+" 되었습니다");
                window.location.href = sucessurl ;
            },
            error: function(error){
                alert(text+" 중 오류가 발생 했습니다");
                window.location.href = sucessurl ;

            }
       });
    });
}
