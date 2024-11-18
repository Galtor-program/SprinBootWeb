package com.example.SpringBootWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Servicio para enviar correos electrónicos utilizando `JavaMailSender`.
 * <p>
 * Esta clase proporciona una funcionalidad sencilla para enviar correos electrónicos desde la aplicación
 * utilizando el servicio de correo electrónico configurado en la aplicación Spring Boot.
 * </p>
 */
@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	/**
     * Envía un correo electrónico utilizando el servicio de correo configurado.
     * <p>
     * Este método crea un mensaje de correo electrónico sencillo utilizando los parámetros proporcionados
     * (destinatario, asunto y cuerpo del mensaje) y lo envía utilizando el objeto `JavaMailSender`.
     * </p>
     *
     * @param toEmail La dirección de correo electrónico del destinatario.
     * @param subject El asunto del correo electrónico.
     * @param body El cuerpo del correo electrónico, que puede contener texto plano.
     */

	public void sendEmail(String toEmail, String subject, String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("felipetorog@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		
		javaMailSender.send(message);
	}
}
