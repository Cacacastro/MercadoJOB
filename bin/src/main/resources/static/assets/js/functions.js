function apagarMensagem(div)
{
    div.style.display="none";
}

function limparFormulario(id)
{
	id = "'#"+ id + "'";
	$(id)[0].reset();
}