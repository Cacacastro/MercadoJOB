function pesquisarUsuarios()
{
	//e.preventDefault();
	//var filtro = document.getElementById("").value;
    const data = new URLSearchParams();
    data.append("fitro","");
    fetch("/api/usuarios/listar", {method: 'POST', body: data})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        var table="";
        for (let i=0;i<json.length;i++) {
        	var adm = json[i].adm ? 'S' : 'N';
        	table+=`<tr><td>${json[i].id}</td>
        				<td>${adm}</td>
                    	<td>${json[i].nome}</td>
                    	<td>${json[i].email}</td>
                    	<td>${json[i].telefone}</td>
                    <td>
                                	<i onclick="alterarUsuario(${json[i].id})" class="ti-pencil-alt" title="Editar"></i>
                                	<i onclick="apagarUsuario(${json[i].id})" class="ti-trash" title="Excluir"></i>	
                                </td>
                    </tr>
                    `;
                    }
        document.getElementById("body-usuarios").innerHTML=table;
    }).catch(function (error) {
        console.error(error);
    });
}

function apagarUsuario(id)
{
    fetch("/api/usuarios/apagar?id="+id, {method: 'GET'})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        limparForm();
        pesquisarUsuarios();
        var divm = document.getElementById("mensagem-usuarios");
        divm.innerHTML=text;
        divm.style.display="block";
        setTimeout(apagarMensagem(divm), 2000); 
    }).catch(function (error) {
        console.error(error);
    });
}

function alterarUsuario(id)
{   
	var udados = document.getElementById("udados");
    fetch("/api/usuarios/buscar?id="+id, {method: 'GET'})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        udados.id.value=json.id;
        udados.adm.value=json.adm;
        udados.nome.value=json.nome;
        udados.email.value=json.email;
        udados.senha.value=json.senha;
        udados.telefone.value=json.telefone;
        pesquisarUsuarios();
    }).catch(function (error) {
        console.error(error);
    });
}

function gravarUsuario()
{
    //event.preventDefault();
    var udados = document.getElementById("udados");
    var jsontext = JSON.stringify(Object.fromEntries(new FormData(udados)));
    fetch("/api/usuarios/cadastrar", {headers: {'Accept': 'application/json','Content-Type': 'application/json'},
                        method: 'POST', 
                        body: jsontext})
    .then(function (response) {
        return response.text(); })
    .then(function (text) {
        // result recebe a resposta do módulo dinâmico
        limparFormUsuario();
        //limparFormulario("udados");
        pesquisarUsuarios();
        
        var divm = document.getElementById("mensagem-usuarios");
        divm.innerHTML= "Gravado com sucesso";
        divm.style.display="block";
        setTimeout(apagarMensagem(divm), 2000); 
    }).catch(function (error) {
        console.error(error);
    });
}

function limparFormUsuario()
{
    var udados = document.getElementById("udados");
    //ldados.reset();
    udados.id.value=0;
    udados.nome.value="";
    udados.email.value="";
    udados.senha.value="";
    udados.telefone.value="";
    //document.getElementById("mensagem").innerHTML="";
}

function teste(a)
{
	console.log(a);
}