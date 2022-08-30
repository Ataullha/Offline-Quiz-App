/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author MAS
 */
public class JavaMailUtil {
    
    /**
     *
     */
    public static String str = randnum()+"";

    public static void sendMail(String recepient) throws Exception {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "offlinequizapp@gmail.com";
        String Password = "offlinequizapp21";
        
        /**
        you want this password ?
        Okey, take it you are welcome and contact with the admin
        **/

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, Password);
            }

        });

        Message message = prepareMessage(session, myAccountEmail,recepient);
        Transport.send(message);
        System.out.println("Email Sent Successfully...!!!");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) throws Exception {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject(recepient);
            message.setSubject("OFFLINE QUIZ APP Singup verfication!");
             Random rand = new Random();
            LocalDateTime date = LocalDateTime.now();
            String html = "<p><B>Please! Verify Your Singup With This 4 Digits Code\n</B></p><p><i>CODE:</i></p>"+str+"<p><code>Thanks</code></p><code><B>OFFLINE QUIZ APP Team</B>"+"\n"+date;
            message.setContent(html,"text/html");
            return message;
         } catch (MessagingException ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @return a random number
     */
    public static int randnum(){
       
        Random rand = new Random();
       return (rand.nextInt(999)+1000);
   }
    

} 
