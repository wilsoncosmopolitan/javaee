<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contactos</title>
<link rel="icon" href="images/phone.png">
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<header>
		<div class="cabecalho">
			<p class="logo">
				<b>Editar Contacto</b>
			</p>
		</div>
	</header>
	<div class="divforma">
		<form name="frmContacto" action="update" class="frm">
			<table>
				<tr>
					<td><input class="entrada" id="caixaid" type="text" name="idcon" readonly value="<% out.print(request.getAttribute("idcon"));%>"></td>
				</tr>
				<tr>
					<td><input class="entrada" type="text" name="nome" placeholder="Nome" value="<% out.print(request.getAttribute("nome"));%>"></td>
				</tr>
				<tr>
					<td><input class="entrada" type="text" name="fone" placeholder="Telefone" value="<% out.print(request.getAttribute("fone"));%>"></td>
				</tr>
				<tr>
					<td><input class="entrada" type="text" name="email" placeholder="E-mail" value="<% out.print(request.getAttribute("email"));%>"></td>
				</tr>
			</table>
			<input class="botao1 p" type="button" value="Salvar"
				onclick="validar()">
		</form>
	</div>
	<footer>
		<div class="rodape">
			<p>Written by Wilson Cosmopolita &copy; 2024</p>
		</div>
	</footer>
	<script src="scripts/validacao.js"></script>
</body>

</html>