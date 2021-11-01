/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.mail.MessagingException;
import model.models.MailFile;
import util.FileUtility;

/**
 *
 * @author Fabio Coimbra
 */
public class DownloadAttachmentService {

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
                item.renameTo(new File(parentFolder.getAbsoluteFile() + "\\" + String.format("%s.%d.%s", filename, i, itemExtension)));
            }
        }
    }

    public void saveAllAttachments(String path, Predicate<File> criteria) throws MessagingException, IOException {
        List<File> myFiles = FileUtility.getFiles(path);
        for (File file : myFiles) {
            if (criteria.test(file)) {
                MailFile mailFile = mailSvc.openMessage(file);
                mailFile.saveAllAttachments(file.getParent());
            }
            renameAttachments(file);
        }
    }

}
