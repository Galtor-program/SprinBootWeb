package com.example.SpringBootWeb.entity.copy;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
/**
 * Entidad que representa un rol dentro de la aplicación.
 * <p>
 * Esta clase mapea la tabla "role" en la base de datos y se utiliza para representar un rol de usuario en el sistema.
 * Un rol puede estar asociado a múltiples usuarios, y cada usuario puede tener uno o más roles.
 * </p>
 */
@Entity
@Table(name="role")
public class RoleEntity {
	/**
     * Identificador único del rol.
     * <p>
     * Este campo mapea la columna `id` de la tabla `role` y se genera automáticamente con la estrategia de identidad.
     * </p>
     */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@ManyToMany(mappedBy= "roles")
	private List<UserEntity> users = new ArrayList<UserEntity>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	 /**
     * Representación en forma de cadena del objeto `RoleEntity`.
     * <p>
     * Este método sobrescrito proporciona una representación legible del objeto, útil para propósitos de depuración.
     * </p>
     * 
     * @return Una cadena que representa el objeto `RoleEntity`.
     */

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", users=" + users + "]";
	}
	
	
	

}
