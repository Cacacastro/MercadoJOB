function apagarMensagem(div)
{
    div.style.display="none";
}

function limparFormulario(id)
{
	document.getElementById(id).reset();
}

function sair()
{
	sessionStorage.removeItem('user');
	window.location.href = "/";
}

function verificaLogado()
{
	if(sessionStorage.getItem('user') == null)
		window.location.href = "entrar.html";
}