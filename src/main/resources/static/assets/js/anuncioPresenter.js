function gravarAnuncio()
{
	event.preventDefault();
    var dados = document.getElementById("anuncios-form");
    var formData = Object.fromEntries(new FormData(dados));
    
    var categorias = document.getElementById("categoria");
    var cat_nome = categorias.options[categorias.selectedIndex].text;
    var cat_id = categorias.options[categorias.selectedIndex].value;
    
    formData.categoria = {id: cat_id, nome: cat_nome};
    
    
    var localidades = document.getElementById("localidade");
    var loc_nome = localidades.options[localidades.selectedIndex].text;
    var loc_id = localidades.options[localidades.selectedIndex].value;
    
    
    formData.localidade = {id: loc_id, nome: loc_nome};
    formData.id = 0;
    
    var usuario = {id: id, adm: false, nome: nome, email: "", senha: "", telefone: ""};
    formData.usuario = usuario;
    
    
    formData.descLonga = document.getElementById("descLonga").value;
    
   
    if(formData.foto1.name != "")
    {	
		enviaImagem(formData.foto1, id+"-1.jpg");
		formData.foto1 = "assets/img/anuncios/"+id+"-1.jpg";	
	}
	else
		formData.foto1 = "";
    if(formData.foto2.name != "")
    {	
		enviaImagem(formData.foto2, id+"-2.jpg");
		formData.foto2 = "assets/img/anuncios/"+id+"-2.jpg";	
	}
	else
		formData.foto2 = "";
	if(formData.foto3.name != "")
    {	
		enviaImagem(formData.foto3, id+"-3.jpg");
		formData.foto3 = "assets/img/anuncios/"+id+"-3.jpg";	
	}
	else
		formData.foto3 = "";
    
    var jsontext = JSON.stringify(formData);
   	
   	console.log(jsontext);
   	
   	
	fetch("/api/anuncios/cadastrar", {headers: {'Accept': 'application/json','Content-Type': 'application/json'},
                        method: 'POST', 
                        body: jsontext})
    .then(function (response) {
        return response.text(); })
    .then(function (text) {
         console.log(text);
    }).catch(function (error) {
        console.error(error);
    });
}

async function enviaImagem(file, nome)
{
	var formData = new FormData();
	formData.append("file", file, nome);
	var options = {
		  method: 'POST',
		  body: formData
		};
	var response = await fetch("/api/anuncios/upload", options);
   	var retorno = await response.text();
    return retorno;
}


function mostrarAnuncioUser()
{
	var formData = new FormData();
	formData.append("codUser", id);
	fetch("/api/anuncios/obteranuncio", {method: 'POST', body: formData 
   		}).then(function (response) {
        	return response.text();
   		}).then(function (text) {
				var json = JSON.parse(text);
				var str = "";
				
				str += "<b>Título do Anúncio: <\/b> " + json.nome;
				
				str += "<br/><br/><b>Preço: <\/b>" + json.valor;
				
				str += "<br/><br/><b>Descrição Curta: <\/b>" + json.descCurta;
				
				str += "<br/><br/><b>Descrição Longa: <\/b>" + json.descLonga;
				
				str += "<br/><br/><b>Categoria: <\/b>" + json.categoria.nome;
				
				str += "<br/><br/><b>Localização: <\/b>" + json.localidade.cidade + " - " + json.localidade.estado;
				
				
				str += "<br/><br/><b>Imagem 1: <\/b> <img src=\"" + json.foto1 + "\"</img>";
				 
				str += "<br/><br/><b>Imagem 2: <\/b> <img src=\"" + json.foto2 + "\"</img>";
				
				str += "<br/><br/><b>Imagem 1: <\/b> <img src=\"" + json.foto3 + "\"</img>";
				
				document.getElementById("meuanuncio").innerHTML=str;
				
				
				
				
				
 		}).catch(function (error) {
        console.error(error);
    });
   
}


function selectCategoria()
{
    const data = new URLSearchParams();
    data.append("fitro","");
    fetch("/listarCat", {method: 'POST', body: data})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        var options="";
        for (let i=0;i<json.length;i++)
        //<option value="2">São Paulo</option>
        if(i == 0)
        	options+=`<option value="${json[i].id}" selected="selected">${json[i].nome}</option>`;
        else
        	options+=`<option value="${json[i].id}">${json[i].nome}</option>`;
        document.getElementById("categoria").innerHTML=options;
    }).catch(function (error) {
        console.error(error);
    });
}


function selectLocalidades()
{
    const data = new URLSearchParams();
    data.append("fitro","");
    fetch("/api/localidades/listar", {method: 'POST', body: data})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        var options="";
        for (let i=0;i<json.length;i++)
        if(i == 0)
        	options+=`<option value="${json[i].id}" selected="selected">${json[i].cidade}</option>`;
        else
        	options+=`<option value="${json[i].id}">${json[i].cidade}</option>`;
        document.getElementById("localidade").innerHTML=options;
    }).catch(function (error) {
        console.error(error);
    });
}