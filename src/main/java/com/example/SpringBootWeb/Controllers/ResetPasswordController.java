package com.example.SpringBootWeb.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SpringBootWeb.service.UserService;
/**
 * Controlador encargado de gestionar el restablecimiento de contraseñas.
 * <p>
 * Este controlador maneja las solicitudes relacionadas con la validación de un token para el restablecimiento de
 * contraseña y el cambio de la contraseña del usuario. Incluye la visualización de la página para cambiar la contraseña
 * y la actualización de la misma en el sistema.
 * </p>
 */
@Controller
public class ResetPasswordController {
	
	@Autowired
	private UserService  userService;

	/**
     * Muestra la página de cambio de contraseña si el token de restablecimiento es válido.
     * <p>
     * Este método maneja la solicitud GET para mostrar la página de cambio de contraseña. Si el token proporcionado es
     * válido, se muestra la página donde el usuario puede ingresar una nueva contraseña. Si el token no es válido,
     * se redirige a una página de mensaje con un error.
     * </p>
     * 
     * @param token El token de restablecimiento de contraseña enviado al usuario.
     * @param model El modelo utilizado para pasar el token a la vista (si es válido).
     * @return La vista correspondiente. Si el token es válido, devuelve "changePassword", de lo contrario, "message".
     */
	@GetMapping("/resetPassword")
	public String resetPasswordWebPage(@RequestParam String token, Map<String,Object> model) {
		
		Boolean tokenValid = userService.validateResetPasswordToken(token);
		if(tokenValid) {
			model.put("resetPasswordToken", tokenValid);
			return "changePassword";
			
		}else {
			return "message";
		}
		
	}
	/**
     * Cambia la contraseña del usuario después de verificar el token de restablecimiento.
     * <p>
     * Este método maneja la solicitud POST para cambiar la contraseña de un usuario. Si el token de restablecimiento
     * es válido, se actualiza la contraseña en el sistema. Si el token no es válido, se redirige a una página de
     * mensaje de error.
     * </p>
     * 
     * @param resetPasswordToken El token de restablecimiento de contraseña proporcionado por el usuario.
     * @param password La nueva contraseña que el usuario quiere establecer.
     * @return La vista a la que se debe redirigir después de cambiar la contraseña. Si el token es válido, redirige
     *         a la página de inicio de sesión con un parámetro indicando que la contraseña ha sido cambiada.
     */
	
	@PostMapping("/changePassword")
	public String changePassword(String resetPasswordToken, String password) {
		Boolean tokenValid = userService.validateResetPasswordToken(resetPasswordToken);
		if(tokenValid) {
			userService.updateChangePassword(resetPasswordToken, password);
		}else {
			return  "message";
		}
		return "redirect:login?changePassword=true";
	}
	
}
