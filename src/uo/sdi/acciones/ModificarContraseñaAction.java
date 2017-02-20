package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.business.Services;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.User;
import uo.sdi.dto.util.Cloner;


public class ModificarContraseñaAction implements Accion {
	

		@Override
		public String execute(HttpServletRequest request,
				HttpServletResponse response) {
			
			String resultado="EXITO";
			
			String nuevaContraseña=request.getParameter("password");
			HttpSession session=request.getSession();
			User user=((User)session.getAttribute("user"));
			User userClone=Cloner.clone(user);
			userClone.setPassword(nuevaContraseña);
			

			
			try {
				UserService userService = Services.getUserService();
				userService.updateUserDetails(userClone);
				Log.debug("Modificada contraseña de [%s] con el valor [%s]", 
						userClone.getLogin(), nuevaContraseña);
				session.setAttribute("user",userClone);
			}
			catch (BusinessException b) {
				Log.debug("Algo ha ocurrido actualizando la contraseña de [%s] a [%s]: %s", 
						user.getLogin(),nuevaContraseña,b.getMessage());
				resultado="FRACASO";
			}
			return resultado;
		}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
