package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.Category;
import uo.sdi.dto.Task;
import uo.sdi.dto.User;
import alb.util.log.Log;

public class ListarTareasAction implements Accion {
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado="EXITO";
		HttpSession session=request.getSession();
		User usuario;
		List<Task> listaTareas;
		List<Category> listaCategorias;
		
		try {
			usuario=(User) session.getAttribute("user");
			
			listaTareas = Services.getTaskService().findAllTasksByUserId(usuario.getId());
			request.getSession().setAttribute("listaTareas", listaTareas);
			
			TaskService taskService = Services.getTaskService();
			listaCategorias=taskService.findCategoriesByUserId(usuario.getId());
			request.setAttribute("listaCategorias", listaCategorias);
			Log.debug("Obtenida lista de categorías conteniendo [%d] categorías", 
					listaCategorias.size());
			
			Log.debug("Obtenida lista de tareas conteniendo [%d] tareas", 
					listaTareas.size());
		}
		catch (BusinessException b) {
			
			Log.debug("Algo ha ocurrido obteniendo lista de tareas: %s",
					b.getMessage());
			resultado="FRACASO";
		}
		return resultado;
	}

	
}
