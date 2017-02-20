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
import uo.sdi.dto.types.UserStatus;



public class ModificarStatusAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado="EXITO";
		
		Long usuarioID =  Long.parseLong(request.getQueryString().split("&")[0].split("=")[1]);
		String login = request.getQueryString().split("&")[0].split("=")[2];
		UserStatus estado;
		List<User> listaUsuarios;
		
		try {
			AdminService adminService = new AdminServiceImpl();
			User user = adminService.findUserById(usuarioID);
			
			//Si el estado actual del usuario esta activado, se desactiva
			if(user.getStatus().equals(UserStatus.ENABLED)){
				adminService.disableUser(usuarioID);
				estado=UserStatus.DISABLED;
				//Sino, se activa
			}else{
				adminService.enableUser(usuarioID);
				estado=UserStatus.ENABLED;
			}
			
			Log.debug("Modificado estado del usuario [%s]: [%s] al estado: [%s]", 
					usuarioID, login, estado);
		
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
