package com.example.SpringBootWeb.entity.copy;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * Entidad que representa a un usuario en la aplicación.
 * <p>
 * Esta clase mapea la tabla `user` en la base de datos y contiene la información básica de un usuario,
 * como su nombre, correo electrónico, contraseña y los roles que tiene asignados. Además, maneja un token
 * de restablecimiento de contraseña y su fecha de generación para facilitar la recuperación de contraseñas.
 * </p>
 */
@Entity
public class UserEntity {

	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long userId;
		
		@Column(nullable = false)
		private String firstName;
		
		@Column(nullable= false)
		private String lastName;
		
		@Column(nullable= false, unique= true)
		private String email;
		
		@Column(nullable= false)
		private String password;
		/**
	     * Lista de roles asignados al usuario.
	     * <p>
	     * Este campo establece una relación muchos a muchos con la entidad `RoleEntity`. 
	     * La relación se maneja a través de una tabla intermedia llamada `user_roles`, donde se mapea
	     * la relación con las columnas `user_id` y `role_id`.
	     * </p>
	     */

		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinTable(name="user_roles", joinColumns =
		@JoinColumn(name="user_id", referencedColumnName="userId"),
		inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id")
		)
		
		
		private List<RoleEntity> roles = new ArrayList <RoleEntity>();
		
		private String resetPasswordToken;
		
		private Timestamp tokenGeneratedDate;
		
		
		
		public String getResetPasswordToken() {
			return resetPasswordToken;
		}

		public void setResetPasswordToken(String resetPasswordToken) {
			this.resetPasswordToken = resetPasswordToken;
		}

		public Timestamp getTokenGeneratedDate() {
			return tokenGeneratedDate;
		}

		public void setTokenGeneratedDate(Timestamp tokenGeneratedDate) {
			this.tokenGeneratedDate = tokenGeneratedDate;
		}

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

		public List<RoleEntity> getRoles() {
			return roles;
		}

		public void setRoles(List<RoleEntity> roles) {
			this.roles = roles;
		}

		/**
	     * Representación en forma de cadena del objeto `UserEntity`.
	     * <p>
	     * Este método sobrescribe el método `toString()` para proporcionar una representación legible
	     * del objeto `UserEntity`, lo cual es útil para depuración y registro de eventos.
	     * </p>
	     * 
	     * @return Una cadena que representa el objeto `UserEntity`.
	     */
		@Override
		public String toString() {
			return "UserEntity [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
					+ email + ", password=" + password + ", roles=" + roles + ", resetPasswordToken="
					+ resetPasswordToken + ", tokenGeneratedDate=" + tokenGeneratedDate + "]";
		}

		
		
		
}
