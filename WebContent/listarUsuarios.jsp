<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
<title>Listado de usuarios: </title>
</head>

<body>

	<div>
		<div>
			<h1 align="center">Usuarios</h1>
			<table align="center">
							<th><a href="ordenarUsuarios?id=login">LOGIN</a></th>
					<th><a href="ordenarUsuarios?id=email">EMAIL</a></th>
					<th><a href="ordenarUsuarios?id=status">STATUS</a></th>

				<c:forEach var="entry" items="${listaUsuarios}" varStatus="i">
					<tr class="row" id="item_${i.index}">
						<td>${entry.id}</td>
						<td>${entry.login}</td>
						<td>${entry.email}</td>
						
						<c:if test="${entry.isAdmin==true}">
							<td>${entry.status}</td>
							<td></td>
						</c:if>
						
						<!--  SI el usuario no es un admin, se puede desactivar-->
						<c:if test="${entry.isAdmin==false}">
							<td><a id="status_link_id" href="modificarStatus?id=${entry.id}=${entry.login}">${entry.status}</a></td>
							<td><a id="delete_link_id${entry.id}" href="eliminarUsuario?id=${entry.id}=${entry.login}" onclick="return confirm('seguro que desea eliminar al usuario?')">Eliminar</a></td>
						</c:if>
						
					</tr>
				</c:forEach>
			</table>
		</div>
		<center>
			<a id="paginaAnterior_link_id" href="principalUsuario">Atrás.</a>
			<%@ include file="pieDePagina.jsp"%>
		</center>
	</div>
</body>
</html>