package uo.sdi.acciones;


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

public class EliminarCategoriaAction implements Accion {
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado="EXITO";
		HttpSession session=request.getSession();
		User usuario;
		TaskService taskService = Services.getTaskService();
		usuario=(User) session.getAttribute("user");
		@SuppressWarnings("unchecked")
		Map<Category,List<Task>> listaTaskCategoria=(Map<Category, List<Task>>) session.getAttribute("listaTaskCategoria");
		@SuppressWarnings("unchecked")
		List<Category> categorias=(List<Category>)session.getAttribute("listaCategorias");
			try {
				for(Category cat:taskService.findCategoriesByUserId(usuario.getId())){
					if(cat.getName().equals(request.getParameter("categoria"))){
							Category control=null;
							for(Category cate:categorias){
								if(cate.getName().equals(cat.getName()))
									control=cate;
							}
							if(control!=null)categorias.remove(control);
							for(Category cate:listaTaskCategoria.keySet()){
								if(cate.getName().equals(cat.getName()))
									control=cate;
							}
							if(control!=null)listaTaskCategoria.remove(control);
							taskService.deleteCategory(cat.getId());
							session.setAttribute("listaTaskCategoria",listaTaskCategoria);
							session.setAttribute("listaCategorias",categorias);
							return resultado;
					}
				}
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return resultado;
	}

	
}

