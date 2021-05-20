function pesquisar()
{
	//e.preventDefault();
	console.log("pesquisar");
	//var filtro = document.getElementById("").value;
    const data = new URLSearchParams();
    data.append("fitro","");
    fetch("/listarCat", {method: 'POST', body: data})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        var table="";
        for (let i=0;i<json.length;i++)
        table+=`<tr><td>${json[i].id}</td><td>${json[i].nome}</td>
                    <td>
                    	<i onclick="alterar(${json[i].id})" class="ti-pencil-alt" title="Editar"></i>
                    	<i onclick="apagar(${json[i].id})" class="ti-trash" title="Excluir"></i>
                    </td>
                    </tr>`;
        document.getElementById("respesq").innerHTML=table;
    }).catch(function (error) {
        console.error(error);
    });
}

function gravar()
{
    //event.preventDefault();
    var fdados = document.getElementById("fdados");
    var jsontext = JSON.stringify(Object.fromEntries(new FormData(fdados)));
    fetch("/cadastrarCat", {headers: {'Accept': 'application/json','Content-Type': 'application/json'},
                        method: 'POST', 
                        body: jsontext})
    .then(function (response) {
        return response.text(); })
    .then(function (text) {
        // result recebe a resposta do módulo dinâmico
        limparForm();
        pesquisar();
    }).catch(function (error) {
        console.error(error);
    });
}

function apagar(id)
{
    fetch("/apagarCat?id="+id, {method: 'GET'})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        limparForm();
        pesquisar();
        var divm=document.getElementById("mensagem");
        divm.innerHTML=text;
        divm.style.display="block";
        setTimeout(apagarMensagem, 2000); 
    }).catch(function (error) {
        console.error(error);
    });
}

function alterar(id)
{   var fdados = document.getElementById("fdados");
    fetch("/buscarCat?id="+id, {method: 'GET'})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        fdados.id.value=json.id;
        fdados.nome.value=json.nome;
        pesquisar();
    }).catch(function (error) {
        console.error(error);
    });
}


function limparForm()
{
    var fdados = document.getElementById("fdados");
    //fdados.reset();
    fdados.id.value=0;
    fdados.nome.value="";
    //document.getElementById("mensagem").innerHTML="";
}

