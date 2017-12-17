/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.web.services;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.model.AuthToken;
import com.fabiocompany.supermercadosdeltaplus.model.service.IAuthTokenService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUserService;
import com.fabiocompany.supermercadosdeltaplus.service.SimpleResponse;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.mail.SimpleMailMessage;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping(value = "/")
public class PasswordRecoverRSController extends BaseRSController{
	private static Logger LOG = LoggerFactory.getLogger(PasswordRecoverRSController.class);
	@Autowired
	private IAuthTokenService tokenService;
	
	@Autowired
	IUserService userService;

    private MessageSource messages;
	
    private JavaMailSender mailSender;
	
	@RequestMapping(value = "/sendmailtoresetpassword", method = RequestMethod.GET)
	public ResponseEntity<Object> sendMailToResetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) throws ServiceException, NotFoundException {
		try {
			User user = userService.loadByEmail(userEmail);
			if (user == null) {
				throw new NotFoundException();
			}
			
			AuthToken at=new AuthToken(86400, user.getUsername());
			String tokenuser=at.encodeCookieValue();
			tokenService.save(at);
			
			//establezco las propiedades de la conexión
			Properties props = new Properties();
			// Nombre del host de correo, es smtp.gmail.com
			props.setProperty("mail.smtp.host", "smtp.gmail.com");

			// TLS si está disponible
			props.setProperty("mail.smtp.starttls.enable", "true");

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port","587");

			// Nombre del usuario
			props.setProperty("mail.smtp.user", user.getUsername()+"@gmail.com");

			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");
			
			//creo la sesión para el envío de mail		
	        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
			
	        //mando un mail con contenido con formato MIME
			MimeMessage message = new MimeMessage(session);
			
			// Quien envia el correo
			message.setFrom(new InternetAddress(user.getUsername()+"@gmail.com"));
			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			message.setSubject("SupermercadosDelta Plus: Cambio de contraseña");
			message.setText("Usted está pidiendo un cambio de contraseña. "
					+ "Su nombre de usuario es: "+user.getUsername()+".\n"
					+ "Use el siguiente link para recuperar su contraseña: "
					+ "http://localhost:8080/supermercadosdeltaplus/index.html#!/cambiarcontrasenia?token="+tokenuser);			

			//mando el mail con el objeto Transport
			Transport t = session.getTransport("smtp");
			// Aqui usuario y password de gmail
			t.connect(userEmail,"ricardo,fantastico.204");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			
			return new ResponseEntity<Object>("Mail enviado", HttpStatus.OK);
		}
		catch (AddressException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 catch (ServiceException e) {
			 LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
