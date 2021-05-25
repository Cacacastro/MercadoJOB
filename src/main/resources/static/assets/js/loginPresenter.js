function entrar()
{
	event.preventDefault();
	var dados = document.getElementById("login-form");
	var jsontext = JSON.stringify(Object.fromEntries(new FormData(dados)));
	
	fetch("/api/usuario/entrar", {headers: {'Accept': 'application/json','Content-Type': 'application/json'},
                        method: 'POST', 
                        body: jsontext})
    .then(function (response) {
        return response.text(); })
    .then(function (text) {
		if(text == 'email ou senha incorretos' || text == 'Usuário não encontrado') {
			console.log(text);
		}
		else {
			var json = JSON.parse(text);
			sessionStorage.setItem('user', text);
			if(json.adm)
				window.location.href = "contaadmin.html";
			else
				window.location.href = "contauser.html";
		}
    }).catch(function (error) {
        console.error(error);
    });
	
}

function registrar()
{
	event.preventDefault();
    var dados = document.getElementById("registro-form");
    var jsontext = JSON.stringify(Object.fromEntries(new FormData(dados)));
    if(dados.senha.value != dados.senhaConfirma.value)
    	console.log("Confirmação de senha inválida")
    else {
		fetch("/api/usuarios/registrar", {headers: {'Accept': 'application/json','Content-Type': 'application/json'},
                        method: 'POST', 
                        body: jsontext})
    .then(function (response) {
        return response.text(); })
    .then(function (text) {
        
        limparFormulario("registro-form");
         console.log("usuario registrado");
    }).catch(function (error) {
        console.error(error);
    });
	}
}


function verificaSessao()
{
	if(sessionStorage.getItem('user') != null)
	{
		var u = JSON.parse(sessionStorage.getItem('user'));
		if(u.admin)
			window.location.href = "contaadmin.html";
		else
			window.location.href = "contauser.html";
	}
}



