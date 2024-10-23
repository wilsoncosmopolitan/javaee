function validar()
{
	let nome = frmContacto.nome.value
	let fone = frmContacto.fone.value
	if (nome === ""){
		alert("Campo nome obrigatório")
		frmContacto.nome.focus()
		return false
	} else if(fone === ""){
		alert("Campo telefone obrigatório")
		frmContacto.fone.focus()
		return false
	} else
		document.forms["frmContacto"].submit()
}