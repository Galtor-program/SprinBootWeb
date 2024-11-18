# SpringBootWeb
### Descripción
SpringBootWeb es una aplicación web basada en Spring Boot que implementa un sistema de registro y autenticación de usuarios. 
Esta aplicación incluye funcionalidades para el registro de usuarios, inicio de sesión, recuperación de contraseña y gestión de roles.
Utiliza MySQL como base de datos y Gmail SMTP para el envío de correos electrónicos.

### Características principales:
* Registro y autenticación de usuarios.
* Recuperación de contraseña mediante enlace de restablecimiento.
* Roles de usuario con permisos básicos (por ejemplo, rol "USER").
* Interfaz de usuario simple para interactuar con la aplicación.
* Seguridad configurada con Spring Security.
* Envío de correos electrónicos mediante SMTP de Gmail para la recuperación de contraseñas.
#
# Tecnologías utilizadas
* Java 17
* Spring Boot 3.x
* Spring Security
* Spring Data JPA
* Spring Web
* Spring Mail
* ModelMapper para conversión de DTO a entidad.
* MySQL como base de datos.
* JSP como motor de plantillas.
#
# Requisitos previos
Asegúrate de tener lo siguiente instalado y configurado en tu máquina:
* Java 17 o superior.
* MySQL y tener una base de datos configurada.
* Maven para la gestión de dependencias.
* Spring Boot: La aplicación está basada en Spring Boot 3.x.

#
### Configuración del proyecto
1. Clona este repositorio
Primero, clona este repositorio a tu máquina local:
git clone https://github.com/tu-usuario/SpringBootWeb.git
cd SpringBootWeb

2. Configura MySQL
Asegúrate de tener MySQL instalado y configurado. Crea una base de datos llamada login_db y configura las credenciales de acceso.
* CREATE DATABASE login_db;

3. Configura application.properties
En el archivo src/main/resources/application.properties, realiza las siguientes configuraciones:

Conexión a la base de datos MySQL:
* spring.datasource.url=jdbc:mysql://localhost:3306/login_db
* spring.datasource.username=root
* spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update

Configuración de correo electrónico (para recuperación de contraseña):
* spring.mail.host=smtp.gmail.com
* spring.mail.port=587
* spring.mail.username=xxxx@gmail.com
* spring.mail.password=xxxxx
* spring.mail.properties.mail.smtp.auth=true
* spring.mail.properties.mail.smtp.starttls.enable=true
* Nota: Asegúrate de reemplazar xxxx@gmail.com y xxxxx por las credenciales de tu cuenta de correo de Gmail.

#
### Flujo de la Aplicación
Registro de usuario:

El usuario puede registrarse a través de la página de registro en /userRegistrationPage.
Al completar el formulario de registro, se envía una solicitud POST a /registerUser, donde se valida si el correo electrónico ya está registrado.
Si no existe un usuario con el correo proporcionado, el sistema lo registra y lo redirige a la página de inicio de sesión.
Inicio de sesión:

El usuario puede iniciar sesión en la aplicación en la página /login.
Si las credenciales son correctas, el usuario es redirigido a la página de inicio /home.
Recuperación de contraseña:

Si el usuario olvida su contraseña, puede solicitar un enlace para restablecerla desde /viewForgotPassword.
El sistema enviará un correo con un enlace de restablecimiento a la dirección de correo proporcionada.
El usuario puede restablecer su contraseña accediendo al enlace que contiene un token único.
Cambio de contraseña:

Una vez que el usuario accede al enlace con el token, se le permitirá cambiar la contraseña a través de la página /changePassword.





  
