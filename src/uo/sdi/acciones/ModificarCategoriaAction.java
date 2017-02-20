package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.Category;
import uo.sdi.dto.Task;
import uo.sdi.dto.User;
import uo.sdi.dto.util.Cloner;


public class ModificarCategoriaAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado="EXITO";
		HttpSession session=request.getSession();
		User usuario;			
		usuario=(User) session.getAttribute("user");
		
		TaskService taskService = Services.getTaskService();
		try {
		String nuevoNombre=request.getParameter("Nuevo_Nombre");
		long idCat=Integer.parseInt(request.getParameter("categoria"));
		Category categoria=taskService.findCategoryById(idCat);
		categoria.setName(nuevoNombre);
		taskService.updateCategory(categoria);
		
		/*listaTareas = taskService.findAllTasksByUserId(usuario.getId());
		request.getSession().setAttribute("listaTareas", listaTareas);*/
		session.setAttribute("listaTareasHoy", taskService.findTodayTasksByUserId(usuario.getId()));

		session.setAttribute("listaTareasSemana", taskService.findWeekTasksByUserId(usuario.getId()));
		
		session.setAttribute("listaTareasInbox", taskService.findInboxTasksByUserId(usuario.getId()));
		
		session.setAttribute("listaCategorias", taskService.findCategoriesByUserId(usuario.getId()));
		
		
		
		Map<Category,List<Task>> listaTaskCategoria = new HashMap<Category, List<Task>>();
		for(Category cat:taskService.findCategoriesByUserId(usuario.getId())){
			List<Task> listaTk= new ArrayList<Task>();
			for(Task tk:taskService.findAllTasksByUserId(usuario.getId())){
				if(tk.getCategoryId().equals(cat.getId())){
					listaTk.add(tk);
				}
			}
			listaTaskCategoria.put(cat,listaTk);
		}
		session.setAttribute("listaTaskCategoria", listaTaskCategoria);

		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
}
