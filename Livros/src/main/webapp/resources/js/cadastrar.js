function validarDados(){
	if( $('#formEstabelecimento\\:cnpj').val() != "" &&
		isNumeric( $('#formEstabelecimento\\:cnpj').val() ) &&
		$('#formEstabelecimento\\:nome').val() != "" &&
		$('#formEstabelecimento\\:senha').val() != "" ){
		return true;
	}
	$('#alert').addClass('alert-danger');
	$('#alert').text('Dados inválidos');
	return false;
}

function isNumeric(str) {
	var er = /^[0-9]+$/;
	return (er.test(str));
};// isNumeric()