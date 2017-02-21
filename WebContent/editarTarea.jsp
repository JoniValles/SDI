<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
<title>Editar tarea</title>
</head>
<body>
	<div>
		<center>
			<h3 align="center">Editar tarea</h3>
		</center>
		<form action="editarTarea" method="POST">
			<table style="width: 70%" align="center">
				<tr>
					<th>Title:</th>
					<td id="title"><input type="text" name="title">${titulo}</td>
				</tr>
				<tr>
					<th>Comentarios:</th>
					<td id="comment"><textarea rows="6" cols="100">
						${comentarios}
					</textarea></td>
				</tr>
				<tr>
					<th>Fecha:</th>
					<td id="planned"><input type="text" name="planned">${entrega}</td>
				</tr>
				<tr>
					<th>Categorias:</th>
					<td align="center">
						<select name="categoria" size="1">
							<option value="a">Ninguna</option>
							<c:forEach var="entry" items="${listaCategorias}" varStatus="i">
								<option value="${entry.id}">${entry.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td/>
					<td><input type="submit" value="Editar Tarea"></td>
				</tr>
			</table>
		</form>
		<center>
			<a id="paginaAnterior_link_id" href="login.jsp">Volver atrás</a>
			<%@ include file="pieDePagina.jsp"%>
		</center>
	</div>
</body>
</html>