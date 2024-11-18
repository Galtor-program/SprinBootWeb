package com.example.SpringBootWeb.Controllers;

import java.util.Map;

import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SpringBootWeb.service.EmailSenderService;
import com.example.SpringBootWeb.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


/**
 * Controlador encargado de gestionar las solicitudes relacionadas con la recuperacion de contraseña
 */
@Controller
public class ForgotPasswordController {
	
	/**
	 * Servicio encargado de gestionar las operaciones relacionadas con los usuarios
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailSenderService emailSenderService;
	/**
	 * Muestra la pagina de recuperación de contraseña
	 * Este método maneja la solicitud de tipo GET para mostrar la página donde el usuario puede ingresar su correo
	 * @return nombre de la vista (forgotpassword)
	 */
	@GetMapping("/viewForgotPassword")
	public String viewForgotPasswordPage() {
		
		return "forgotpassword";
	}
	/**
	 * Procesa solicitud de recuperación de contraseña y envía correo electronico con enlace para
	 * restablecer contraseña
	 * Este método maneja la solicitud POST que permite al usuario solicitar el restablecimiento de contraseña ingresando
     * su correo electrónico. Si el correo electrónico existe, se genera un token de restablecimiento y se envía un
     * enlace por correo electrónico al usuario. Si el correo no existe, se muestra un mensaje de error.
	 * @param email correo necesario para restablecer contraseña
	 * @param model muestra mensajes en vista
	 * @param request request El objeto HttpServletRequest utilizado para obtener la URL de la solicitud actual.
	 * @return vista de recuperacion (forgotpassword)
	 */
	
	@PostMapping("/sendEmail")
	public String sendEmail(String email, Map<String, Object> model, HttpServletRequest request) {
		
		Boolean emailExists =userService.findByEmail(email);
		
		if(emailExists) {
			//token de reseteo de password y link
			String token =RandomString.make(25);
			
			String webUrl = request.getRequestURL().toString(); // http://localhost:8080/sendEmail
			String link = webUrl.replace(request.getServletPath(),""); //http://localhost:8080
			String restPasswordLink= link+"/resetPassword?token="+token;
			
			// actualizar el token de reseteo de contrasena en la tabla 
			userService.updateResetPasswordToken(email, token);
			
			// enviar el correo al usuario 
			String emailSubject = "Ingrese al link para resetear su contrasena";
			emailSenderService.sendEmail(email, emailSubject, restPasswordLink);
			model.put("successMessage", "Se ha enviado a su correo el link para recuperar contrasena");
			
			
		}else {
			model.put("errorMessage", "Usuario no existe.");
		}
		
		return "forgotpassword";
	}

}
