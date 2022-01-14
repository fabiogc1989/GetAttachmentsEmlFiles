/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.models.MailFile;
import util.FileUtility;

/**
 *
 * @author Fabio Coimbra
 */
public class DownloadAttachmentService {
	
	private static Logger logger = LogManager.getLogger(DownloadAttachmentService.class);

    private MailService mailSvc;

    public DownloadAttachmentService(MailService mailSvc) {
        this.mailSvc = mailSvc;
    }

    private void renameAttachments(File file) {
        String filename = file.getName().substring(0, file.getName().lastIndexOf('.'));
        File parentFolder = new File(file.getParent());
        for (int i = 1; i <= parentFolder.listFiles().length; i++) {
            File item = parentFolder.listFiles()[i - 1];
            if (item.isFile() == true) {
                String itemExtension = item.getName().substring(item.getName().lastIndexOf('.') + 1);
                File futureFile = new File(parentFolder.getAbsoluteFile() + "\\" + String.format("%s.%d.%s", filename, i, itemExtension));
                if (FileUtility.hasFile(futureFile)) {
                	futureFile.delete();
                }
                
                item.renameTo(futureFile);
                
                logger.trace("the file '" + item.getName() + "' was renamed to '" + futureFile.getName() + "'");
            }
        }
    }

    public void saveAllAttachments(String path, Predicate<File> criteria) throws MessagingException, IOException {
    	logger.info("Start saving attachments for each eml files");
    	List<File> myFiles = FileUtility.getFiles(path);
        for (File file : myFiles) {
            if (criteria.test(file)) {
            	logger.info("Open Message from " + file.getAbsoluteFile());
                MailFile mailFile = mailSvc.openMessage(file);
                logger.trace("Message read successfully");
                mailFile.saveAllAttachments(file.getParent());
                renameAttachments(file);
            }
            
        }
    }

}
