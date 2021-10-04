let formulario = document.getElementById("formulario");

function isLetter(str) {
	return str.length === 1 && str.match(/[a-z]/i);
}

function isCharNumber(c) {
	return c >= '0' && c <= '9';
}

formulario.addEventListener("submit", function(e){
	let nome = document.getElementById("username");
	let senha = document.getElementById("password");
	let confirmacaoSenha = document.getElementById("password_confirm");
	
	let erroValidacaoNome = false;
	let erroValidacaoSenha = false;
	
	nome = nome.value;
	senha = senha.value;
	confirmacaoSenha = confirmacaoSenha.value;
	
	for(let i = 0; i < nome.length; i++){
		if(!isLetter(nome[i]) && !isCharNumber(nome[i])){
			erroValidacaoNome = true;
		}
	}
		
	if(senha != confirmacaoSenha){
		erroValidacaoSenha = true;
	}
	
	if(erroValidacaoNome || erroValidacaoSenha){
		if(erroValidacaoNome){
			alert("Nome contém caracteres inválidos");
		}else{
			alert("Senhas inseridas não são iguais");
		}
		
		formulario.reset();
		e.preventDefault();
	}
});