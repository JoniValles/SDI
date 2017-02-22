<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head> <title>TaskManager - Inicie sesión</title>
<body>
  <form action="validarse" method="post" name="validarse_form_name">

 	<center><h1>TaskManager - Inicie sesión</h1></center>
 	<hr><br>
 	<table align="center">
    	<tr> 
    		<td align="right"><span></span>Su identificador de usuario</td>
	    	<td><input type="text" name="nombreUsuario" align="right" size="15"></td>
	    	
      	</tr>
      					<tr>
					<th align="left"><span></span>Su contraseña:</th>
					<td colspan="3"><input id="password" type="password"
						name="password" align="left" size="15"></td>
						<td><input id="validar_button_id" type="submit" value="Enviar"/></td>
				</tr>
      					<tr>
					<td/>
					<td>
					<a id="registrarse_link_id" align="left" href="accesoRegistroUsuario">Registrarse</a>
					</td>
				</tr>
      	
      </table>
   </form>
   <a id="listarCategorias_link_id" href="listarCategorias">Lista de categorias</a>
   <%@ include file="pieDePagina.jsp" %>
</body>
</html>