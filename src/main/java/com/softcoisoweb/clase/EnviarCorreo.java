/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.clase;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author manue
 */
public class EnviarCorreo {

    public String  EnviarCorreo(String[] correo, String asunto, StringBuilder content) throws MessagingException {
        Properties propiedad = new Properties();
        propiedad.put("mail.smtp.auth", "true");
        propiedad.put("mail.smtp.starttls.enable", "true");
        propiedad.put("mail.smtp.host", "smtp.gmail.com");
        propiedad.put("mail.smtp.port", "587");
        String resultado = "Error";
        final String correoEnvia = "lymapre@gmail.com";
        final String contraseña = "37957987398";

        Session session = Session.getInstance(propiedad, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEnvia, contraseña);
            }
        });
       
        try{
            Message message = new MimeMessage(session);
            BodyPart texto = new MimeBodyPart();
            texto.setContent(content.toString(), "text/html; charset=utf-8");
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            texto = new MimeBodyPart();
            
             DataSource imagenCabecera = new FileDataSource("C:\\Users\\manue\\Documents"
                     + "\\NetBeansProjects\\SoftCoisoWeb\\imagen\\CabezaEmail.PNG");
            texto.setDataHandler(new DataHandler(imagenCabecera));
            texto.setHeader("Content-ID", "<imagenCabezera>");
            multiParte.addBodyPart(texto);
            
            message.setFrom(new InternetAddress(correoEnvia));
            for (int i = 0; i < correo.length; i++) {
               message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(correo[i]));
            }
            message.setSubject(asunto);
            message.setContent(multiParte);
            Transport.send(message);
            resultado="Exitoso";

        } catch (AddressException ex) {
            System.out.println("Error al enviar el correo electronico: "+ex);
        } 
        
        return resultado;
    }

}
