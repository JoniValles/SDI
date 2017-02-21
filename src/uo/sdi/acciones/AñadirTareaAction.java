package uo.sdi.acciones;


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


public class AñadirTareaAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		Task task;
		String resultado="EXITO";
		String tarea = request.getParameter("Tarea");
		
		
		
		
		try {
			HttpSession  sesion = request.getSession();
			
			task = toTask(((User)sesion.getAttribute("user")), tarea);
			task.setTitle(tarea);			
			TaskService taskService = Services.getTaskService();
			taskService.createTask(task);
			
			Log.debug("Se ha creado una nueva tarea [%s] ",
					tarea);
			
		}
		catch (BusinessException b) {
			Log.debug("Algo ha ocurrido al crear la nueva tarea.");
			resultado="FRACASO";
		}
		return resultado;
	}
	
	
	
	private Task toTask(User user, String tarea ){
		return new Task()
		.setUserId(user.getId())
		.setTitle(tarea)
		.setCategoryId(Long.parseLong("1"));
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}