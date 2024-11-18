package com.example.SpringBootWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootWeb.entity.copy.UserEntity;

/**
 * Repositorio de acceso a datos para la entidad `UserEntity`.
 * <p>
 * Esta interfaz extiende `JpaRepository`, lo que proporciona acceso a una serie de operaciones CRUD estándar
 * para la entidad `UserEntity`. Además, define métodos personalizados para realizar consultas más específicas, 
 * como buscar usuarios por su correo electrónico o por su token de restablecimiento de contraseña.
 * </p>
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
	/**
     * Busca un usuario en la base de datos por su correo electrónico.
     * <p>
     * Este método se utiliza para recuperar un usuario de la tabla `user` utilizando su correo electrónico. 
     * Si el usuario con el correo especificado existe, se devuelve la entidad correspondiente, 
     * de lo contrario, se retorna `null`.
     * </p>
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return El usuario con el correo electrónico especificado, o `null` si no se encuentra.
     */
	UserEntity findByEmail(String email);
	/**
     * Busca un usuario en la base de datos por su token de restablecimiento de contraseña.
     * <p>
     * Este método se utiliza para recuperar un usuario utilizando el token de restablecimiento de contraseña
     * almacenado en la base de datos. Si el token es válido y existe en la base de datos, se devuelve la entidad 
     * correspondiente, de lo contrario, se retorna `null`.
     * </p>
     *
     * @param token El token de restablecimiento de contraseña a buscar.
     * @return El usuario asociado al token de restablecimiento de contraseña, o `null` si no se encuentra.
     */

	UserEntity findByResetPasswordToken(String token);
}