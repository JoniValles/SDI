<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
<title>Registro</title>
</head>

<body>
	<div class="container">
			<h1 align="center">TaskManager - Registro de Usuario</h1>
			
		<form action="registrarUsuario" method="POST">
			<table style="width: 50%" align="center">
				<tr>
					<th>Login:</th>
					<td id="login"><input type="text" name="login" placeholder="Login"></td>
				</tr>
				<tr>
					<th>Email:</th>
					<td id="email"><input type="text" name="email" size="15"
						placeholder="Email"></td>
				</tr>
				<tr>
					<th>Contraseña:</th>
					<td id="password"> <input type="text" name="password"
						placeholder="Contraseña"></td>
				</tr>
				<tr>
					<th>Repetir contraseña:</th>
					<td id="password2"><input type="text" name="password2"
						placeholder="Repita la contraseña"></td>
				</tr>
				<tr>
					<td/>
					<td><input id="registrarse_link_id" type="submit" placeholder="Registrarse"></td>
				</tr>
			</table>
		</form>
		<center>
			<a href="login.jsp">Atrás</a>
			<%@ include file="pieDePagina.jsp"%>
		</center>
	</div>
</body>
</html>