package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		//List<Task> listaTareas;
		List<Task> listaTareasHoy;
		List<Task> listaTareasSemana;
		List<Task> listaTareasInbox;
		List<Category> listaCategorias;
		Map<Category,List<Task>> listaTaskCategoria = new  HashMap<Category,List<Task>>();
		
		try {
			usuario=(User) session.getAttribute("user");
			
			TaskService taskService = Services.getTaskService();
			
			/*listaTareas = taskService.findAllTasksByUserId(usuario.getId());
			request.getSession().setAttribute("listaTareas", listaTareas);*/
			listaTareasHoy = taskService.findTodayTasksByUserId(usuario.getId());
			session.setAttribute("listaTareasHoy", listaTareasHoy);
			
			listaTareasSemana = taskService.findWeekTasksByUserId(usuario.getId());
			session.setAttribute("listaTareasSemana", listaTareasSemana);
			
			listaTareasInbox= taskService.findInboxTasksByUserId(usuario.getId());
			session.setAttribute("listaTareasInbox", listaTareasInbox);
			
			listaCategorias=taskService.findCategoriesByUserId(usuario.getId());
			session.setAttribute("listaCategorias", listaCategorias);
			
			for(Category cat:listaCategorias){
				List<Task> listaTk= new ArrayList<Task>();
				for(Task tk:taskService.findAllTasksByUserId(usuario.getId())){
					if(tk.getCategoryId().equals(cat.getId())){
						listaTk.add(tk);
					}
				}
				listaTaskCategoria.put(cat,listaTk);
			}
			session.setAttribute("listaTaskCategoria", listaTaskCategoria);
			
			Log.debug("Obtenida lista de categorías conteniendo [%d] categorías", 
					listaCategorias.size());
			
			//Log.debug("Obtenida lista de tareas conteniendo [%d] tareas", 
				//	listaTareas.size());
		}
		catch (BusinessException b) {
			
			Log.debug("Algo ha ocurrido obteniendo lista de tareas: %s",
					b.getMessage());
			resultado="FRACASO";
		}
		return resultado;
	}

	
}

