/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.mail.MessagingException;
import model.models.MailFile;
import model.services.MailService;

/**
 *
 * @author Fabio Coimbra
 */
public class Program {

    public static void main(String[] args) {

        Properties props = System.getProperties();
        props.put("mail.smtp.Host", "smtp-mail.Outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        try {
            File emlFile = new File("C:\\temp\\test.eml");
            
            MailService mailService = new MailService(props, null);
            MailFile mailFile = mailService.openMessage(emlFile);
            mailFile.saveAllAttachments("C:\\temp");
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (MessagingException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}
