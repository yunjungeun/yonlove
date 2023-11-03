function checkLoginForm() {
    var userId = document.getElementById("exampleInputEmail").value;
    var password = document.getElementById("exampleInputPassword").value;
    var errorMessage = document.getElementById("errorMessage");

    if (userId === "" || password === "") {
        errorMessage.innerText = "ID와 password 확인하세요";
        return false; // 폼 제출을 막음
    } else {
        errorMessage.innerText = ""; // 에러 메시지 초기화
        return true; // 폼 제출을 허용
    }
}
