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
							<td><a href="modificarStatus?id=${entry.id}=${entry.login}">${entry.status}</a></td>
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