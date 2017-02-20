<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="eliminarCategoria" method="POST">
		<tr>
		<c:forEach var="entry" items="${listaCategorias}" varStatus="i">
			
				<td>${entry.name}</td>
				<td>
					<input type="radio" name="categoria" value="${entry.name}"> /<br>  
					</td>

		</c:forEach>
		
		<td><input type="submit" value="Eliminar" onclick="return confirm('seguro que desea eliminar la categoria?')"></td>
		</tr>
</form>