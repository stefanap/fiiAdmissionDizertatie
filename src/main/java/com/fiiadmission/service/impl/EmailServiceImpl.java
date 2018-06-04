package com.fiiadmission.service.impl;

import com.elasticemail.app.API;
import com.elasticemail.app.ApiTypes;
import com.elasticemail.app.functions.Email;
import com.fiiadmission.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${email.fromEmail}")
    private String fromEmail;

    @Value("${email.fromEmail}")
    private String fromName;

    public void sendEmail(String subject, String to, String bodyText, String bodyHtml){
        API.API_KEY = "be48621a-9116-441b-9eaf-5ff8f6410371";
        Email email = new Email();
        ApiTypes.EmailSend response = null;

        ApiTypes.StringArray toStringArray = new ApiTypes.StringArray();
        toStringArray.add(to);
        try {
            response = email.send(subject, fromEmail, fromName, "", "","", "", "" , "" , toStringArray, null , null , null , null, null, "", "", "", bodyHtml, bodyText, "" , "", "", ApiTypes.EncodingType.BASE64, "", null, null, "", null, "", "", false, null, false, false);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        if (response != null)
        {
            System.out.println("MsgID to store locally: " + response.messageid); // Available only if sent to a single recipient
            System.out.println("TransactionID to store locally: " + response.transactionid);
        }

    }

//    public static void main( String[] args ) throws Exception {
//        API.API_KEY = "be48621a-9116-441b-9eaf-5ff8f6410371";
//
//        Email email = new Email();
//        ApiTypes.EmailSend response = null;
//        String subject = "Test sending email from fii.";
//        String fromEmail = "test1@test.com";
//        String fromName = "FII Admission";
//        String bodyText = "Text body";
//        String bodyHtml = "Hello there";
//
////        FileData attachmentFile = FileData.CreateFromFile("C:/Users/recipients.csv");
////        List<FileData> files = new ArrayList<FileData>();
////        files.add(attachmentFile);
//
//        ApiTypes.StringArray to = new ApiTypes.StringArray();
//        to.add("nicolae.abacioaiei@gmail.com");
//        try {
//            response = email.send(subject, fromEmail, fromName, "", "","", "", "" , "" , to, null , null , null , null, null, "", "", "", bodyHtml, bodyText, "" , "", "", ApiTypes.EncodingType.BASE64, "", null, null, "", null, "", "", false, null, false, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//
//        if (response != null)
//        {
//            System.out.println("MsgID to store locally: " + response.messageid); // Available only if sent to a single recipient
//            System.out.println("TransactionID to store locally: " + response.transactionid);
//        }
//    }
}