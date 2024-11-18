package com.example.SpringBootWeb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuración personalizada de Spring Security para la aplicación.
 * <p>
 * Esta clase configura aspectos clave de la seguridad en la aplicación, como la codificación de contraseñas,
 * las reglas de autorización de acceso a las rutas y el comportamiento de los formularios de inicio de sesión y cierre de sesión.
 * </p>
 */
@Configuration
public class SpringSecurityCustomConfiguration {

    Logger logger = LoggerFactory.getLogger(SpringSecurityCustomConfiguration.class);
    
    /**
     * Crea un bean de `PasswordEncoder` utilizando el algoritmo BCrypt.
     * <p>
     * El `PasswordEncoder` es utilizado para codificar las contraseñas de los usuarios antes de ser almacenadas en la base de datos.
     * El algoritmo BCrypt es una opción segura y comúnmente utilizada para la codificación de contraseñas.
     * </p>
     * 
     * @return Un `PasswordEncoder` configurado con BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Configura la seguridad de la aplicación, definiendo las reglas de autorización de las solicitudes HTTP.
     * <p>
     * Este método establece las reglas de seguridad para las rutas de la aplicación, la configuración del inicio de sesión,
     * y el manejo del cierre de sesión. 
     * </p>
     *
     * @param http El objeto `HttpSecurity` utilizado para configurar las reglas de seguridad.
     * @return Un `SecurityFilterChain` que se aplica a las solicitudes HTTP.
     * @throws Exception Si ocurre un error en la configuración de la seguridad.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("-- Spring Security Configuration");
        return http
            .authorizeHttpRequests(authorize -> 
                authorize.requestMatchers("/home").authenticated()
                    .anyRequest().permitAll()
            )
            .formLogin(login -> 
                login.loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .failureUrl("/login?error=true")
            )
            .logout(logout->
            logout.invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout=true")
            )
           
            .build();
    }
}
