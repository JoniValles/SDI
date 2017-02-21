<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Tabs - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#tabs" ).tabs();
  } );
  </script>
</head>
<body>

	<div>

	</div>
<div id="tabs">
  <ul>
  		
	<li><table style="width: 50%">
			<form action="añadirTarea" method="POST">
				<tr>
					<th>Nueva Tarea:</th>
					<td id="Tarea"><input type="text" name="Tarea" value=""></td>
				</tr>
				<tr>
					<td/>
					<td><input type="submit" value="Enviar"></td>
				</tr>	
			</form>
		</table>			
	 </li>
    <li><a href="#tabs-1">Hoy</a></li>
    <li><a href="#tabs-2">Semana</a></li>
    <li><a href="#tabs-3">Inbox</a></li>
    <li><a href=<%="#tabs-4"%>>Configuracion</a></li>
    <%!int j=4; %>
    <c:forEach var="entry" items="${listaTaskCategoria}" varStatus="i">
	    <%j++; %>
	   	<li><a href=<%="#tabs-"+j%>>${entry.key.name}</a></li>
    </c:forEach>
  </ul>
  <div id="tabs-1"> 
  <table border="1" align="center">
  <tr>
  	<th>Categoría</th>
  	<th>Título</th>
  	<th>Comentario</th>
  	<th>Creada</th>
  	<th>Planeada</th>
  </tr>
    <c:forEach var="entry" items="${listaTareasHoy}" varStatus="i">
			<tr class="row" id="item_${i.index}">
						<td><c:out value="${entry.category}"/></td>	
						<td><c:out value="${entry.title}"/></td>				
						<td><c:out value="${entry.comments}"/></td>	
						<td><c:out value="${entry.created}"/></td>
						<td>
						<c:if test="${entry.pasado}">
								<font color="red">
							</c:if>
							<c:out value="${entry.planned}"/>
						<c:if test="${entry.pasado}">
							</font>
						</c:if>
						</td><td>
						<form action="acabarTarea" method="POST">
							<input type="hidden" name="tarea" value="${entry.id}">
							<input type="submit" value="Terminar">
						</form>
						</td>
						<td>
						<form action="editarTarea" method="POST">
							<input type="hidden" name="tarea" value="${entry.id}">
							<input type="submit" value="Editar">
						</form>	
						</td>
				</tr>	
	</c:forEach>
	</table>
  </div>
  <div id="tabs-2">
  <table border="1" align="center">
  <tr>
  	<th>Categoría</th>
  	<th>Título</th>
  	<th>Comentario</th>
  	<th>Creada</th>
  	<th>Planeada</th>
  </tr>
    <c:forEach var="entry" items="${listaTareasSemana}" varStatus="i">
			<tr class="row" id="item_${i.index}">	
						<td>
						<c:if test="${entry.pasado}">
								<font color="red">
						</c:if>
							<c:out value="${entry.category}"/>	
						<c:if test="${entry.pasado}">
							</font>
						</c:if>		
						</td>
						<!-- Ref al editarTarea.jsp -->
						<td>${entry.title}(<a id="editarTarea${entry.title}" href="editarTarea?id=${entry.id}"  >Edit</a>)</td> 
						<td><c:out value="${entry.title}"/></td>	
						<td><c:out value="${entry.comments}"/></td>	
						<td><c:out value="${entry.created}"/></td>
						<td><c:out value="${entry.planned}"/></td><td>
						<form action="acabarTarea" method="POST">
							<input type="hidden" name="tarea" value="${entry.id}">
							<input type="submit" value="Terminar">
						</form>
						</td>
						<td>
						<form action="editarTarea" method="POST">
							<input type="hidden" name="tarea" value="${entry.id}">
							<input type="submit" value="Editar">
						</form>	</td>
			</tr>
	</c:forEach>
	</table>
  </div>
  <div id="tabs-3">
  <table border="1" align="center">
  <tr>
  	<th>Categoría</th>
  	<th>Título</th>
  	<th>Comentario</th>
  	<th>Creada</th>
  	<th>Planeada</th>
  </tr>
    <c:forEach var="entry" items="${listaTareasInbox}" varStatus="i">
			<tr class="row" id="item_${i.index}">
						<td><c:out value="${entry.category}"/></td>	
						<td><c:out value="${entry.title}"/></td>				
						<td><c:out value="${entry.comments}"/></td>	
						<td><c:out value="${entry.created}"/></td>
						<td>
						<c:if test="${entry.pasado}">
								<font color="red">
							</c:if>
							<c:out value="${entry.planned}"/>
						<c:if test="${entry.pasado}">
							</font>
						</c:if>
						</td><td>
						<form action="acabarTarea" method="POST">
							<input type="hidden" name="tarea" value="${entry.id}">
							<input type="submit" value="Terminar">
						</form>
						</td>
						<td>
						<form action="editarTarea" method="POST">
							<input type="hidden" name="tarea" value="${entry.id}">
							<input type="submit" value="Editar">
						</form>	</td>
				</tr>
	</c:forEach>
	</table>
  </div>
  	<div id=<%="tabs-4" %>>
  	 <table>
  	 		<tr>
  	 			<td><%@ include file="AñadirCat.jsp"%></td>
			</tr>
		</table>
		<table>
		<tr>
				<td><%@ include file="EliminarCat.jsp"%></td>
		</tr>
		</table>
		<table>
		<tr>
				<td><%@ include file="ConfigurarCat.jsp"%></td>
		</tr>
		</table>
	</div>
  <%j=4; %>
 
  <c:forEach var="ent" items="${listaTaskCategoria}" varStatus="i">
  	<%j++; %>
			<div id=<%="tabs-"+j %>>
			<table border="1" align="center">
				<tr>
  					<th>Categoría</th>
				  	<th>Título</th>
				  	<th>Comentario</th>
				  	<th>Creada</th>
				  	<th>Planeada</th>
  				</tr>
				
	    		<c:forEach var="entry" items="${ent.value}" varStatus="j">
					<tr class="row" id="item_${j.index}">
						<td><c:out value="${entry.category}"/></td>	
						<td><c:out value="${entry.title}"/></td>				
						<td><c:out value="${entry.comments}"/></td>	
						<td><c:out value="${entry.created}"/></td>
						<td>
						<c:if test="${entry.pasado}">
								<font color="red">
							</c:if>
							<c:out value="${entry.planned}"/>
						<c:if test="${entry.pasado}">
							</font>
						</c:if>
						</td>
						<td>
						<form action="acabarTarea" method="POST">
							<input type="hidden" name="tarea" value="${entry.id}">
							<input type="submit" value="Terminar">
						</form>
						</td>
						<td>
						<form action="editarTarea" method="POST">
							<input type="hidden" name="tarea" value="${entry.id}">
							<input type="submit" value="Editar">
						</form>	
						</td>
					</tr>
				</c:forEach>
				</table>
 			</div>
	</c:forEach>
	
	<%j=4; %>

	
</div>
		<center>
			<a href="principalUsuario.jsp">Atrás</a>
			<%@ include file="pieDePagina.jsp"%>
		</center>
</body>
</html>
