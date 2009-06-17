/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.mail;

import br.ufc.ivela.commons.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author marcus
 */
public class MailSender {

    public static void send(String[] to,
            String subject, String content) {

        String smtpHost = Constants.MAIL_SMTP_HOST;
        int smtpPort = Constants.MAIL_SMTP_PORT;
        String from = Constants.MAIL_FROM;

        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);

        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setSubject(subject);
            msg.setSentDate(new java.util.Date());
            msg.setText(content);
            msg.setHeader("Content-Type", "text/html");

            for (int i = 0; i < to.length; i++) {
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
                // Send the message
                Transport.send(msg);
            }
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        MailSender.send(new String[] {"leoomoreira@gmail.com"}, "assunto", "conteudo");
    }
}
