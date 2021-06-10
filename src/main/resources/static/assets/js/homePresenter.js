function exibirTodosAnuncios()
{
    fetch("/api/anuncios/listar/")
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        str = "";
     	for (let i=0;i<json.length;i++)
        {
			str += `<div class="job-list">
              <div class="thumb">
                <a href="#"><img src="${json[i].foto1}" width="100px" height="100px" alt=""></a>
              </div>
              <div class="job-list-content">
                <h4><a href="#">${json[i].nome}</a></h4>
                <div class="descr">
                  <p>${json[i].descCurta}</p>
                  <span>
                    R$ ${json[i].valor}
                  </span>
                </div>
                <div class="clear"></div>
                <div class="job-tag">
                  <div class="pull-left">
                    <div class="meta-tag">
                      <span><a href=""><i class="ti-brush"></i>${json[i].categoria.nome}</a></span>
                      <span><i class="ti-location-pin"></i>${json[i].localidade.cidade}, ${json[i].localidade.uf}</span>
                      <span><i class="fa fa-phone"></i>${json[i].usuario.telefone}</span>
                    </div>
                  </div>
                  <div class="pull-right">
                    
                    <a href="detalhes.html?id=${json[i].id}" class="btn btn-common btn-rm">Mais Detalhes</a>
                  </div>
                </div>
              </div>
            </div>`	
		}
        document.getElementById("anuncios").innerHTML=str;
        
    }).catch(function (error) {
        console.error(error);
    });
}


function montarSelect()
{
    /*const data = new URLSearchParams();
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
    });*/
}