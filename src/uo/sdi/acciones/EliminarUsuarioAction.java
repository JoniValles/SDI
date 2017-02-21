package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.Accion;
import uo.sdi.business.AdminService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.admin.AdminServiceImpl;
import uo.sdi.dto.User;



public class EliminarUsuarioAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado="EXITO";
		
		Long usuarioID =  Long.parseLong(request.getQueryString().split("&")[0].split("=")[1]);
		String login = request.getQueryString().split("&")[0].split("=")[2];
		List<User> listaUsuarios;
		
		try {
			AdminService adminService = new AdminServiceImpl();
			
			//Si el estado actual del usuario esta activado, se desactiva

			adminService.deepDeleteUser(usuarioID);

			
			Log.debug("Eliminado el usuario [%s]: [%s]", 
					usuarioID, login);
		
			listaUsuarios=adminService.findAllUsers();
			request.setAttribute("listaUsuarios", listaUsuarios);
						
		}
		catch (BusinessException BE) {
			Log.debug("Algo ha ocurrido actualizando el estado del usuario [%s]", 
					usuarioID);
			resultado="FRACASO";
		}
		return resultado;
	}
	
}
