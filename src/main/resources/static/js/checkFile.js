function checkFile(text) {
    const fileInput = document.getElementById('fileInput');

    const allowedExtensions =/(\.jpg|\.jpeg|\.png|\.mp4)$/i;

    const maxFileSize = 5 * 1024 * 1024;

    const file = fileInput.files[0];

    if (!file) {
        alert("파일을 선택해주세요.");
        return;
    }

    if (!allowedExtensions.test(file.name)) {
        alert("허용되지 않는 파일 확장자입니다.");
        return;
    }

    if (file.size > maxFileSize) {
        alert("파일 크기가 너무 큽니다.");
        return;
    }

    // 파일 업로드 로직
    alert("파일 "+text+"성공!");
}