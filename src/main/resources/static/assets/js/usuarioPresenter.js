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