function Login()
{
	event.preventDefault();
	var email=document.getElementById("email").value;
	var senha=document.getElementById("senha").value;
	
	if(email == 'admin' && senha == 'admin')
		window.location.href = "contaadmin.html";
		
	if(email == 'user' && senha == 'user')
		window.location.href = "contauser.html";
	
	form.reset();
}