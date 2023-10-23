    // search 할때, 기본 URL 주소값을 변경하기 위해..
    function submitForm() {
        var baseUrl = url

        //  위의 select의 name인 서치타입을 가져오고 해당 입력값을 가져옴
        var searchTypeSelect = document.getElementById("searchType");
        var selectedSearchType = searchTypeSelect.value;
        var search = document.getElementById("search").value;


        // 프로젝트Id 로 검색 또는 입력값이 없을때
        if (search !== "") {
            var searchUrl = baseUrl + "?"+selectedSearchType+"=" + search;
            window.location.href = searchUrl;
        }else{
            window.location.href = baseUrl;
        }

        return false;
    }
         // "검색" 버튼 클릭 시 이벤트 핸들러
    document.querySelector('input[type="submit"]').addEventListener("click", function(event) {
        event.preventDefault(); // 폼 제출 방지
        submitForm();
    });

    // Enter 키를 눌렀을 때 검색 버튼 클릭 시키는 이벤트 핸들러 추가
    document.getElementById("content").addEventListener("keyup", function(event) {
        if (event.key === "Enter") {
            submitForm();
        }
    });