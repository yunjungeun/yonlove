/*js변수에 저장된 머스테치 변수를 불러옴*/
var parsedJson = JSON.parse(jsonValue);

var dropdown = document.getElementById("fk");

for(var key in parsedJson){
    var option = document.createElement("option");
    option.value = key;
    option.text = parsedJson[key];
    dropdown.appendChild(option);
}
