/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.models;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Fabio Coimbra
 */
public class MailFile {

    private String ContentType;
    private String subject;
    private List<Address> fromList = new ArrayList<>();
    private List<MimeBodyPart> attachments = new ArrayList<>();
    private String body;

    public MailFile() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String ContentType) {
        this.ContentType = ContentType;
    }
    
    public List<Address> getFromList(){
        return fromList;
    }

    public void addFrom(Address address) {
        fromList.add(address);
    }

    public void removeFrom(Address address) {
        fromList.remove(address);
    }
    
    public List<MimeBodyPart> getAttachments(){
        return attachments;
    }

    public void addAttachment(MimeBodyPart part) {
        attachments.add(part);
    }

    public void removeAttachment(MimeBodyPart part) {
        attachments.remove(part);
    }

    public void saveAttachment(int index, String outPath) throws IndexOutOfBoundsException, MessagingException, UnsupportedEncodingException, IOException {
        MimeBodyPart part = getAttachments().get(index);
        part.saveFile(outPath + "\\" + MimeUtility.decodeText(part.getFileName()));
    }
    
    public void saveAllAttachments(String outPath) throws MessagingException, UnsupportedEncodingException, IOException{
        for(MimeBodyPart part : getAttachments()){
            part.saveFile(outPath + "\\" + MimeUtility.decodeText(part.getFileName()));
        }
    }
}
