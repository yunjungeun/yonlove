var scenario = ["scenario1","scenario2"];

for(var i = 0; i< {{fkList.scenaro_name}}.length; i++ ){
}

var dropdown = document.getElementById("fk");


for(var i = 0; i< scenario.length; i++){
    var option = document.createElement("option");
    option.value = scenario[i];
    option.text = scenario[i];
    dropdown.appendChild(option);
}
