package com.example.SpringBootWeb.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SpringBootWeb.dto.UserDTO;
import com.example.SpringBootWeb.service.UserService;


/**
 * Controlador de aplicación encargado de manejar solicitudes HTTP.
 * Gestiona registro de usuarios, verficación de correos y navegación
 * 
 */

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Muestra página de registro de usuario
	 * @return nombre de la vista del registro.
	 */
	
	@GetMapping ("/userRegistrationPage")
	public String userRegistrationPage() {
		
		return "userRegistration";
	}
	/**
	 * Formulario de registro de usuario
	 * @param userDTO objeto que contiene los datos del usuario
	 * @param model permite mostrar mensaje en la vista
	 * @return retorna un error y vuelve a la vista inicial o redirige a la vista de login 
	 */
	
	@PostMapping("/registerUser")
	public String registerUser(UserDTO userDTO, Map<String, Object> model) {
		
		Boolean userExists = userService.findByEmail(userDTO.getEmail());
		if(userExists) {
			model.put("errorMessage", "Ya existe una cuenta con este correo!");
			return "userRegistration";
		}else {
		userService.registerUser(userDTO);
		return "redirect:login?userRegistered=true";
		}
		
	}
	/**
	 * 
	 * verifica si un correo electronico ya está registrado en el sistema
	 * Este método es invocado por una solicitud AJAX, asi comprueba la disponibilidad 
	 * del correo durante el registro
	 * 
	 * @param email es el correo a verficar
	 * @return true or false si es que el correo existe.
	 */
	
	@PostMapping("/findByEmail")
	@ResponseBody
	public Boolean findByEmail(String email) {
		
		Boolean emailExists = userService.findByEmail(email);
		if(emailExists) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
			
	}
}
