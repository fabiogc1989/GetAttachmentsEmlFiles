
package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;

import model.services.DownloadAttachmentService;
import model.services.MailService;
import util.LoggerPrinter;

/**
 *
 * @author Fabio Coimbra
 */
public class Program {
	
	private static final LoggerPrinter<Program> logger = new LoggerPrinter<Program>(Program.class);

    public static void main(String[] args) {
    	
    	logger.info("Program started");

    	logger.info("Get properties for JavaMail");
        Properties props = System.getProperties();
        
        logger.info("mail.smtp.host = smtp-mail.Outlook.com");
        props.put("mail.smtp.host", "smtp-mail.Outlook.com");
        logger.info("mail.smtp.port = 587");
        props.put("mail.smtp.port", "587");
        logger.info("mail.smtp.starttls.enable = true");
        props.put("mail.smtp.starttls.enable", "true");

        try {
            MailService mailService = new MailService(props, null);
            DownloadAttachmentService downloadAttachService = new DownloadAttachmentService(mailService);
            
            logger.info("Start saving attachments for each eml files");
            downloadAttachService.saveAllAttachments("C:\\temp\\folder1", f -> f.getName().endsWith(".eml"));

        } catch (FileNotFoundException ex) {
            logger.fatal(ex.getMessage());
        } catch (IOException ex) {
        	logger.fatal(ex.getMessage());
        } catch (MessagingException ex) {
        	logger.fatal(ex.getMessage());
        } catch (Exception ex) {
        	logger.fatal(ex.getMessage());
        }
    }
}
