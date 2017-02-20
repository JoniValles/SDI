package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.business.Services;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.User;

public class RegistrarUsuarioAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String resultado = "EXITO";
		//Se piden los parametros de cada input con el nombre " "
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String password2 = request.getParameter("rePass");
		UserService userService = Services.getUserService();

		
		//Si el email no coincide con el formato foo@foo.com -> Error
		if (!email.matches("[-\\w\\.]+@\\w+\\.\\w+")) {
			request.setAttribute("mensajeParaElUsuario",
					"Error. Formato del correo inválido.");
			resultado = "FRACASO";
			return resultado;
		}

		//Se comprueba que ambas contraseñas sean iguales
		if (!password.equals(password2)) {
			request.setAttribute("mensajeParaElUsuario",
					"Error. Las contraseñas no coinciden.");
			resultado = "FRACASO";
			return resultado;

		} else {
			//Si las contraseñas son iguales se comprueba que cumplan con el formato tipico de contraseña
			if (password.length() < 8) {
				
				request.setAttribute("mensajeParaElUsuario",
						"La contraseña debe contener al menos 8 caracteres.");
				resultado = "FRACASO";
				return resultado;
				} 
		}

		try {
			//Se crea el usuario nuevo con los parametros establecidos
			User user = new User();
			user.setEmail(email);
			user.setIsAdmin(false);
			user.setLogin(login);
			user.setPassword(password);

			//Se registra al usuario
			userService.registerUser(user);
			request.setAttribute("mensajeParaElUsuario", "registro");
			
		} catch (BusinessException p) {
			request.setAttribute("mensajeParaElUsuario", "Este usuario ya existe.");
			resultado = "FRACASO";
		}
		return resultado;
	}

}
