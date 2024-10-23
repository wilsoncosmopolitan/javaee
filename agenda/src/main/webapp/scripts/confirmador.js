/**
 * Confirmação de Exclusão de um Contacto 
 * @author Wilson Cosmopolita
 * @param idcon
 */

function confirmar(idcon){
	let resposta = confirm("Confirma a exclusão deste contacto?")
	if(resposta === true){
		window.location.href = "delete?idcon=" + idcon;
	}
}