/*js변수에 저장된 머스테치 변수를 불러옴*/
var scenario = JSON.parse(test);

var dropdown = document.getElementById("fk");

for(var i = 0; i< scenario.length; i++){
    var option = document.createElement("option");
    option.value = 'scenario_name';
    option.text = scenario[i];
    dropdown.appendChild(option);
}
