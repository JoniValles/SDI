package uo.sdi.acciones;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.Accion;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.Task;
import uo.sdi.dto.User;

import javax.servlet.http.HttpSession;


public class EditarTareaAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String resultado="EXITO";
		String title = request.getParameter("title");
		String comment = request.getParameter("comment");
		String category = request.getParameter("category");
		HttpSession  session = request.getSession();
		User user = (User)session.getAttribute("user");
		Long categoryID = null;
		Long taskId = (Long)session.getAttribute("editTarea");
		Date fecha = null;
		Task task;
		
		try{
			categoryID = Long.parseLong(category);
			
		}catch(NumberFormatException e){
			categoryID = null;
		}
		

		try {
			TaskService taskService = Services.getTaskService();
			//Almacena los datos de toTask
			task = toTask(user, title, comment, categoryID, fecha);
			task.setId(taskId);
			
			//Actualiza la tarea
			taskService.updateTask(task);
			
			session.removeAttribute("editTarea"); 
			Log.debug("El usuario [%s] ha modificado con la tarea [%s].", 
					user.getLogin(), title);
		}
		catch (BusinessException b) {
			Log.debug("El usuario [%s] no ha podido editar la tarea."
					+ " [%s]", user.getLogin(), title);
			resultado="FRACASO";
		}
		return resultado;
	}
	
	private Task toTask(User user, String nombre, String comentario,
			Long CategoriaID, Date fecha){
		return new Task()
		.setUserId(user.getId())
		.setTitle(nombre)
		.setComments(comentario)
		.setCategoryId(CategoriaID)
		.setPlanned(fecha);
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}