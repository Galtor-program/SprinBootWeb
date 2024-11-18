package com.example.SpringBootWeb.Controllers;

import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Gestiona solicitudes de autenticación y navegación
 */


@Controller
public class LoginController {

	/**
	 * 
	 * @param error Indica si hubo un error al iniciar sesion
	 * @param logout Indica si el usuario cerró sesión correctamente
	 * @param userRegistered Indica si la cuenta fue creada exitosamente
	 * @param changePassword Indica si la contraseña fue actualizada
	 * @param model envia mensajes e la vista
	 * @return retorna el nombre de la vista (login).
	 */
	@GetMapping (value={"/","/login"})
	public String Login(@RequestParam(value = "error", defaultValue = "false") Boolean error, 
			@RequestParam(value="logout", defaultValue = "false") Boolean logout,
			@RequestParam(value="userRegistered", defaultValue= "false") Boolean userRegistered, 
			@RequestParam(value="changePassword", defaultValue= "false") Boolean changePassword,
						
			Map<String, Object> model) {
		if(error == Boolean.TRUE) {
			model.put("errorMessage", "Usuario o Contraseña Incorrectos");
		}else if(logout == Boolean.TRUE) {
			model.put("logoutMessage", "Sesión Cerrada Correctamente");
		}else if(userRegistered == Boolean.TRUE) {
			model.put("logoutMessage", "Cuenta Creada Exitosamente!");
		}else if(changePassword == Boolean.TRUE) {
			model.put("logoutMessage", "Contraseña actualizada correctamente!");
		}
		
		
		return "login";
	}
	
	/**
	 * Muestra la pagina de inicio de la aplicación
	 * @return el nombre de la vista de inicio (home)
	 */
	@GetMapping("/home")
	public String home () {
		return "home";
	}
}
