/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import model.models.MailFile;

/**
 *
 * @author Fabio Coimbra
 */
public class MailService {

    private Session mailSession;

    public MailService(Properties props, Authenticator authenticator) {
        mailSession = Session.getInstance(props, authenticator);
    }

    public MailFile openMessage(String filePath) throws FileNotFoundException, MessagingException, IOException {
        InputStream source = new FileInputStream(filePath);
        Message message = new MimeMessage(mailSession, source);

        MailFile mailFile = new MailFile();
        mailFile.setContentType(message.getContentType());
        mailFile.setSubject(message.getSubject());

        Address[] fromArray = message.getFrom();
        for (int i = 0; i < fromArray.length; i++) {
            mailFile.addFrom(fromArray[i]);
        }

        Object content = message.getContent();
        if (content instanceof String) {
            mailFile.setBody((String) content);
        } else {
            Multipart multiPart = (Multipart) message.getContent();
            
            for (int i = 0; i < multiPart.getCount(); i++) {
                MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
                if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                    mailFile.addAttachment(part);
                } else {
                    mailFile.setBody((String)part.getContent());
                }
            }
        }
        
        return mailFile;
    }
    
    public MailFile openMessage(File file) throws MessagingException, IOException{
        return this.openMessage(file.getAbsolutePath());
    }
}
