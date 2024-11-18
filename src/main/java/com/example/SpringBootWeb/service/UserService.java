package com.example.SpringBootWeb.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.SpringBootWeb.dto.UserDTO;

/**
 * Servicio para gestionar operaciones relacionadas con los usuarios.
 * <p>
 * Esta interfaz extiende `UserDetailsService`, lo que permite la integración con Spring Security para la autenticación de usuarios.
 * Además, proporciona métodos específicos para registrar un nuevo usuario, validar un correo electrónico, manejar los tokens de restablecimiento de contraseña
 * y actualizar la contraseña del usuario.
 * </p>
 */
public interface UserService extends UserDetailsService{
	
	 /**
     * Registra un nuevo usuario en la base de datos.
     * <p>
     * Este método toma un objeto `UserDTO`, que contiene la información del nuevo usuario, 
     * y lo almacena en la base de datos. La contraseña del usuario debe ser codificada antes de guardar.
     * </p>
     *
     * @param userDTO El objeto `UserDTO` que contiene los datos del nuevo usuario.
     */
	void registerUser(UserDTO userDTO);
	
	 /**
     * Busca un usuario en la base de datos por su correo electrónico.
     * <p>
     * Este método verifica si ya existe un usuario registrado con el correo electrónico proporcionado.
     * </p>
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return `true` si el correo electrónico ya está registrado, de lo contrario `false`.
     */
	Boolean findByEmail(String email);
	
	/**
     * Actualiza el token de restablecimiento de contraseña para un usuario.
     * <p>
     * Este método genera un nuevo token de restablecimiento de contraseña y lo asigna al usuario con el correo electrónico dado.
     * El token es utilizado para verificar la identidad del usuario antes de permitir el restablecimiento de su contraseña.
     * </p>
     *
     * @param email El correo electrónico del usuario para el cual se va a generar un token.
     * @param token El token de restablecimiento de contraseña.
     */
	
	void updateResetPasswordToken(String email, String token);
	/**
     * Valida un token de restablecimiento de contraseña.
     * <p>
     * Este método verifica si el token de restablecimiento de contraseña proporcionado es válido.
     * </p>
     *
     * @param token El token de restablecimiento de contraseña a validar.
     * @return `true` si el token es válido, de lo contrario `false`.
     */
	
	Boolean validateResetPasswordToken (String token);
	/**
     * Actualiza la contraseña de un usuario utilizando un token de restablecimiento de contraseña.
     * <p>
     * Este método permite a un usuario cambiar su contraseña utilizando un token de restablecimiento que ha sido previamente validado.
     * </p>
     *
     * @param resetPasswordToken El token de restablecimiento de contraseña.
     * @param password La nueva contraseña del usuario.
     */
	
	void updateChangePassword(String resetPasswordToken, String password);
	

}
