package com.example.SpringBootWeb.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.SpringBootWeb.dto.UserDTO;
import com.example.SpringBootWeb.entity.copy.UserEntity;

/**
 * Componente de mapeo que convierte entre objetos `UserDTO` y `UserEntity`.
 * <p>
 * Esta clase utiliza la biblioteca `ModelMapper` para realizar la conversión de manera sencilla y eficiente.
 * El mapeo es bidireccional, permitiendo convertir de `UserDTO` a `UserEntity` y viceversa.
 * </p>
 */
@Component
public class LoginModelMapper {
	
	private ModelMapper modelMapper =  new ModelMapper();
	
	/**
     * Convierte un objeto `UserDTO` a un objeto `UserEntity`.
     * <p>
     * Este método toma los datos de un `UserDTO` (que es un objeto de transferencia de datos) y los mapea a un
     * `UserEntity` (una entidad JPA que se utiliza para interactuar con la base de datos).
     * </p>
     *
     * @param userDTO El objeto `UserDTO` que contiene los datos del usuario.
     * @return El objeto `UserEntity` con los mismos datos del `UserDTO`.
     */
	public UserEntity convertDTOToEntity(UserDTO userDTO) {
		return modelMapper.map(userDTO, UserEntity.class);
				
	}
	
	/**
     * Convierte un objeto `UserEntity` a un objeto `UserDTO`.
     * <p>
     * Este método toma los datos de un `UserEntity` (que representa a un usuario en la base de datos) y los mapea a un
     * `UserDTO` (que es un objeto de transferencia de datos que se utiliza en la capa de presentación o API).
     * </p>
     *
     * @param userEntity El objeto `UserEntity` que contiene los datos del usuario.
     * @return El objeto `UserDTO` con los mismos datos del `UserEntity`.
     */
	
	public UserDTO convertEntityToDTO(UserEntity userEntity) {
		return modelMapper.map(userEntity, UserDTO.class);
	}

}
