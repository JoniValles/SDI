<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="modificarCategoria" method="POST">
		<tr>
		<input type="submit" value="Cambiar_Nombre_de">
		<select name="categoria">
		<c:forEach var="entry" items="${listaCategorias}" varStatus="i">
				<option value="${entry.id}">"${entry.name}"</option>
		</c:forEach>
		</select>
		<a> a </a>
		<input type="text" name="Nuevo_Nombre">
		</tr>
		
</form>