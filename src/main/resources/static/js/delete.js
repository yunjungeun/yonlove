function confirmDelete(event, url) {
    event.preventDefault(); // 링크의 기본 동작(페이지 이동)을 중지합니다.
    var deleteUrl = event.target.getAttribute('href'); // 삭제 URL 가져오기

    if (confirm("정말로 삭제하시겠습니까?")) {
        // 사용자가 확인을 누르면 삭제를 진행합니다.

        $.ajax({
            url: deleteUrl,
            type: "GET",
            success: function (response) {
                alert("정상적으로 삭제되었습니다");
                window.location.href = url; // 삭제 후 리디렉션할 URL을 설정합니다.
            },
            error: function (error) {
                alert("삭제 중 오류가 발생했습니다");
            }
        });
    }
}

