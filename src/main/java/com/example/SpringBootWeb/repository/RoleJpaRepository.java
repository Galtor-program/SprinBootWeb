package com.example.SpringBootWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringBootWeb.entity.copy.RoleEntity;

/**
 * Repositorio de acceso a datos para la entidad `RoleEntity`.
 * <p>
 * Esta interfaz extiende `JpaRepository` para proporcionar operaciones CRUD básicas sobre la entidad `RoleEntity`,
 * como guardar, eliminar, y buscar roles en la base de datos. Además, incluye un método personalizado para encontrar
 * un rol por su nombre.
 * </p>
 */
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

	  /**
     * Busca un rol en la base de datos por su nombre.
     * <p>
     * Este método se utiliza para encontrar un rol en la tabla `role` utilizando su nombre. Si el nombre del rol
     * existe, se devuelve la entidad correspondiente. Si no, se retorna `null`.
     * </p>
     *
     * @param name El nombre del rol a buscar.
     * @return El rol con el nombre especificado, o `null` si no se encuentra.
     */
	RoleEntity findByName(String name);
}
