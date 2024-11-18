package com.example.SpringBootWeb.dto;

/**
 * Clase DTO (Data Transfer Object) que representa un usuario en la aplicación.
 * <p>
 * Esta clase se utiliza para transferir los datos del usuario entre las capas de la aplicación, como la capa de presentación
 * (controladores) y la capa de servicio. Incluye información básica como el ID, el nombre, el apellido, el correo electrónico
 * y la contraseña del usuario.
 * </p>
 */
public class UserDTO {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	  /**
     * Representación en forma de cadena del objeto `UserDTO`.
     * <p>
     * Este método genera una cadena que incluye todos los campos del objeto, útil para propósitos de depuración
     * o para representar al usuario de manera legible.
     * </p>
     * 
     * @return Una cadena con la representación del objeto `UserDTO`.
     */
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	

}
