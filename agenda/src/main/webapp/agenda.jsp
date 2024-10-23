<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<% 
	@ SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) 
	request.getAttribute("contactos");
%>
<!DOCTYPE html>
<html lang="pt-pt">
<head>
<meta charset="UTF-8">
<title>Agenda de Contactos</title>
<link rel="icon" href="images/phone.png">
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Agenda de Contactos</h1>
	<table id="tabela">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
				<% for(int i = 0; i < lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getIdcon() %></td>
					<td><%=lista.get(i).getNome() %></td>
					<td><%=lista.get(i).getFone() %></td>
					<td><%=lista.get(i).getEmail() %></td>
					<td><a href="select?idcon=<%= lista.get(i).getIdcon() %>" class="botao1">Editar</a>
					<a href="javascript: confirmar(<%= lista.get(i).getIdcon() %>)" class="botao2">Excluir</a> 
					</td>
				</tr>
				<% }  %>
		</tbody>
	</table>
	<a href="novo.html" class="botao1" href="">Novo Contacto</a>
	<a href="report" class="botao2" href="">Relatório</a>
	<script type="text/javascript" src="scripts/confirmador.js"></script>
</body>
</html>