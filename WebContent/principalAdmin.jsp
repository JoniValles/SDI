<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Página principal del admin</title>
</head>
<body>
<i>Iniciaste sesión el <fmt:formatDate pattern="dd-MM-yyyy' a las 'HH:mm" 
								value="${sessionScope.fechaInicioSesion}"/>
								(usuario número ${contador})</i>
	<br/><br/>
	<jsp:useBean id="user" class="uo.sdi.dto.User" scope="session" />
	<table>
		<tr>
			<td>Id:</td><td id="id"><jsp:getProperty property="id" name="user" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td id="email"><form action="modificarEmail" method="POST">
					<input type="text" name="email" size="15"
						value="<jsp:getProperty property="email" name="user"/>"> 
					<input type="submit" value="Modificar">
				</form>
			</td>
		</tr>
			<tr>
			<td>Contraseña:</td>
			<td id="password"><form action="modificarContraseña" method="POST">
					<input type="text" name="password" size="15"
						value="<jsp:getProperty property="password" name="user"/>"> 
					<input type="submit" value="Modificar">
				</form>
			</td>
		</tr>
		<tr>
			<td>Login:</td><td id="login"><jsp:getProperty property="login" name="user" /></td>
		</tr>
		<tr>
			<td>Estado:</td><td id="status"><jsp:getProperty property="status" name="user" /></td>
		</tr>
		<tr>
			<td>Lista de usuarios: </td>
		</tr>
		
		
				<c:if test="${user.getIsAdmin()==true}">
			<div class="alert alert-info">
				<a id="listarUsuarios_link_id" href="listarUsuarios">Listar
					Usuarios</a>
			</div>
		</c:if>
		
		<tr> USUARIOS </tr>
		<c:if test="${user.getIsAdmin()!=true}">
			<div class="alert alert-info">
				<a id="listarTareas_link_id" href="listarTareas">Listar
					Tareas</a>
			</div>
		</c:if>
	</table>
	<br/>	
	<a id="cerrarSesion_link_id" href="cerrarSesion">Cerrar sesión</a>
	
	<%@ include file="pieDePagina.jsp" %>
</body>
</html>
