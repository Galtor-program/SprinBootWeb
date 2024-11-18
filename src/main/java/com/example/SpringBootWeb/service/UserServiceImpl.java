package com.example.SpringBootWeb.service;


import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringBootWeb.dto.UserDTO;
import com.example.SpringBootWeb.entity.copy.RoleEntity;
import com.example.SpringBootWeb.entity.copy.UserEntity;
import com.example.SpringBootWeb.repository.RoleJpaRepository;
import com.example.SpringBootWeb.repository.UserJpaRepository;
import com.example.SpringBootWeb.util.LoginModelMapper;


/**
 * Implementación del servicio `UserService` para gestionar usuarios.
 * <p>
 * Esta clase contiene la lógica de negocio para registrar usuarios, validar correos electrónicos,
 * manejar tokens de restablecimiento de contraseña y cargar usuarios para la autenticación.
 * </p>
 */
@Service
public class UserServiceImpl implements UserService {
	
	/**
     * Repositorio JPA para gestionar las entidades de usuario en la base de datos.
     */
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	 /**
     * Mapper para convertir entre objetos DTO y entidades.
     */
	@Autowired
	private LoginModelMapper loginModelMapper;
	
	 /**
     * Codificador de contraseñas utilizado para encriptar las contraseñas antes de almacenarlas.
     */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
     * Repositorio JPA para gestionar las entidades de roles en la base de datos.
     */
	@Autowired
	private RoleJpaRepository roleJpaRepository;

	/**
     * Registra un nuevo usuario en la base de datos.
     * <p>
     * Este método toma un objeto `UserDTO` que contiene los datos del nuevo usuario, 
     * convierte ese DTO a una entidad `UserEntity`, encripta su contraseña, asigna un rol por defecto
     * y guarda el usuario en la base de datos.
     * </p>
     *
     * @param userDTO El objeto `UserDTO` que contiene la información del nuevo usuario.
     */
	@Override
	public void registerUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
		
		RoleEntity roleEntity = roleJpaRepository.findByName("ROLE_USER");
		
		UserEntity userEntity = loginModelMapper.convertDTOToEntity(userDTO);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity.setRoles(Arrays.asList(roleEntity));
		userJpaRepository.save(userEntity);

	}
	/**
     * Verifica si ya existe un usuario con el correo electrónico proporcionado.
     * <p>
     * Este método busca un usuario en la base de datos utilizando su correo electrónico. 
     * Si el usuario existe, devuelve `true`; de lo contrario, devuelve `false`.
     * </p>
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return `true` si el usuario existe, `false` si no existe.
     */

	@Override
	public Boolean findByEmail(String email) {
		UserEntity userEntity = userJpaRepository.findByEmail(email);
		if(null != userEntity)
			return Boolean.TRUE;
		return Boolean.FALSE;
		
	}
	/**
     * Carga un usuario para la autenticación con Spring Security.
     * <p>
     * Este método es utilizado por Spring Security para cargar un usuario basado en su nombre de usuario
     * (en este caso, el correo electrónico del usuario). Si el usuario no se encuentra, lanza una excepción.
     * </p>
     *
     * @param username El nombre de usuario (correo electrónico) para autenticar.
     * @return Un objeto `UserDetails` que contiene los detalles del usuario para autenticación.
     * @throws UsernameNotFoundException Si el usuario no se encuentra en la base de datos.
     */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity = userJpaRepository.findByEmail(username);
		
		if(userEntity==null) {
			throw new UsernameNotFoundException(username + "not found.");
		}
		
		return User.withUsername(userEntity.getEmail()).password(userEntity.getPassword()).build();
		
	}
	/**
     * Actualiza el token de restablecimiento de contraseña para un usuario dado.
     * <p>
     * Este método genera un nuevo token de restablecimiento de contraseña, lo asigna al usuario
     * y guarda la fecha de generación del token. El token es utilizado para autenticar al usuario
     * en el proceso de restablecimiento de la contraseña.
     * </p>
     *
     * @param email El correo electrónico del usuario para el cual se va a generar un token.
     * @param token El token de restablecimiento de contraseña.
     */

	@Override
	public void updateResetPasswordToken(String email, String token) {
		UserEntity userEntity = userJpaRepository.findByEmail(email);
		userEntity.setResetPasswordToken(token);
		userEntity.setTokenGeneratedDate(new Timestamp(System.currentTimeMillis()));
		userJpaRepository.save(userEntity);
	}
	
	/**
     * Valida un token de restablecimiento de contraseña.
     * <p>
     * Este método verifica si el token de restablecimiento de contraseña es válido. Para ello, comprueba
     * si el token existe y si no ha expirado (el tiempo de expiración es de 5 minutos).
     * </p>
     *
     * @param token El token de restablecimiento de contraseña a validar.
     * @return `true` si el token es válido y no ha expirado, de lo contrario `false`.
     */

	@Override
	public Boolean validateResetPasswordToken(String token) {
		UserEntity userEntity = userJpaRepository.findByResetPasswordToken(token);
		Long inMinutes = null;
		if (userEntity != null) {
			 inMinutes = validateResetPasswordExpiredTime(userEntity); 
		}
		
		
		if(userEntity != null && inMinutes <= 5) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
			
	}
	 /**
     * Calcula el tiempo en minutos desde que se generó el token de restablecimiento de contraseña.
     * <p>
     * Este método calcula la diferencia en minutos entre el momento actual y la fecha en que se generó
     * el token de restablecimiento.
     * </p>
     *
     * @param userEntity El usuario que contiene el token y la fecha de generación.
     * @return La diferencia en minutos entre la fecha de generación del token y el momento actual.
     */

	private Long validateResetPasswordExpiredTime(UserEntity userEntity) {
		Timestamp tokenGeneratedTimeStamp = userEntity.getTokenGeneratedDate();
		
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		Long differenceInMillisSeconds = currentTimestamp.getTime() - tokenGeneratedTimeStamp.getTime();
		return  differenceInMillisSeconds/(60*1000);
		
	}
	/**
     * Actualiza la contraseña de un usuario dado un token de restablecimiento de contraseña válido.
     * <p>
     * Este método permite cambiar la contraseña del usuario utilizando un token de restablecimiento de contraseña.
     * La nueva contraseña se codifica antes de ser almacenada.
     * </p>
     *
     * @param resetPasswordToken El token de restablecimiento de contraseña.
     * @param password La nueva contraseña del usuario.
     */

	@Override
	public void updateChangePassword(String resetPasswordToken, String password) {
		UserEntity userEntity = userJpaRepository.findByResetPasswordToken(resetPasswordToken);
		userEntity.setPassword(passwordEncoder.encode(password));
		userJpaRepository.save(userEntity);
	}

}
