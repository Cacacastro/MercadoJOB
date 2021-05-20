function pesquisarLocalidades()
{
	//e.preventDefault();
	console.log("pesquisar");
	//var filtro = document.getElementById("").value;
    const data = new URLSearchParams();
    data.append("fitro","");
    fetch("/api/localidades/listar", {method: 'POST', body: data})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        var table="";
        for (let i=0;i<json.length;i++)
        	table+=`<tr><td>${json[i].id}</td>
        				<td>${json[i].cidade}</td>
                    	<td>${json[i].estado}</td>
                    	<td>${json[i].uf}</td>
                    
                    <td>
                                	<i onclick="alterarLocalidade(${json[i].id})" class="ti-pencil-alt" title="Editar"></i>
                                	<i onclick="apagarLocalidade(${json[i].id})" class="ti-trash" title="Excluir"></i>
                                </td>
                    </tr>
                    `;
        document.getElementById("body-localidades").innerHTML=table;
    }).catch(function (error) {
        console.error(error);
    });
}


function apagarLocalidade(id)
{
    fetch("/api/localidades/apagar?id="+id, {method: 'GET'})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        limparForm();
        pesquisarLocalidades();
        var divm = document.getElementById("mensagem-localidades");
        divm.innerHTML=text;
        divm.style.display="block";
        setTimeout(apagarMensagem(divm), 2000); 
    }).catch(function (error) {
        console.error(error);
    });
}

function gravarLocalidade()
{
    //event.preventDefault();
    var ldados = document.getElementById("ldados");
    var jsontext = JSON.stringify(Object.fromEntries(new FormData(ldados)));
    fetch("/api/localidades/cadastrar", {headers: {'Accept': 'application/json','Content-Type': 'application/json'},
                        method: 'POST', 
                        body: jsontext})
    .then(function (response) {
        return response.text(); })
    .then(function (text) {
        // result recebe a resposta do módulo dinâmico
        limparFormLocalidade();
        pesquisarLocalidades();
    }).catch(function (error) {
        console.error(error);
    });
}

function alterarLocalidade(id)
{   var ldados = document.getElementById("ldados");
    fetch("/api/localidades/buscar?id="+id, {method: 'GET'})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        ldados.id.value=json.id;
        ldados.cidade.value=json.cidade;
        ldados.estado.value=json.estado;
        ldados.uf.value=json.uf;
        pesquisarLocalidades();
    }).catch(function (error) {
        console.error(error);
    });
}

function limparFormLocalidade()
{
    var ldados = document.getElementById("ldados");
    //fdados.reset();
    ldados.id.value=0;
    ldados.cidade.value="";
    ldados.estado.value="";
    ldados.uf.value="";
    //document.getElementById("mensagem").innerHTML="";
}