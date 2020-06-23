/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.clase;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author manue
 */
public class EnviarCorreo {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

    public String enviarCorreo(String correos, String asunto, StringBuilder cuerpo) throws MessagingException {
        Properties propiedad = new Properties();
        final String correoEnvia = "lymapre@gmail.com";
        final String contraseña = "37957987398";
        propiedad.put("mail.smtp.host", "smtp.google.com");
        propiedad.put("mail.smtp.port", "587");
        propiedad.put("mail.smtp.auth", "true");
        propiedad.put("mail.smtp.socketFactory.port", "587");
        propiedad.put("mail.smtp.socketFactory.fallback", "true");
        propiedad.put("mail.smtp.starttls.enable", "true");
        propiedad.put("mail.smtp.starttls.required", "true");
        propiedad.put("mail.smtp.ssl.enable", "false");
        propiedad.put("#mail.smtp.debug", "true");
        propiedad.put("mail.username", correoEnvia);
        propiedad.put("mail.password", contraseña);

        String resultado = "Error";

        Session session = Session.getInstance(propiedad, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEnvia, contraseña);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoEnvia));
            message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(correos));
            message.setSubject(asunto);
            message.setContent(cuerpo.toString(), "text/html");
            try ( Transport transport = session.getTransport("smtp")) {
                transport.connect("smtp.gmail.com", correoEnvia, contraseña);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                resultado = "Exitoso";
            }

        } catch (AddressException ex) {
            LOGGER.log(Level.SEVERE, "Error al enviar el correo electronico:  {0}", new Object[]{ex});
        }

        return resultado;
    }

}
