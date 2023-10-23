/*js변수에 저장된 머스테치 변수를 불러옴*/
var scenario = JSON.parse(test);

var dropdown = document.getElementById("fk");

for(var key in scenario){
    var option = document.createElement("option");
    option.value = key;
    option.text = scenario[key];
    dropdown.appendChild(option);
}
