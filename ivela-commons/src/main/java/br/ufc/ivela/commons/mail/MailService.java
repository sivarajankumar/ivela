/*    
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: MailService.java                                                                    #
# Document: Mail Service                                                                    # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - marcus (UFC)                      - XXXXXX - Initial Version                #
# 21-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Mail                    #
#############################################################################################
*/
package br.ufc.ivela.commons.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class MailService {

    private Log log = LogFactory.getLog(getClass());

    private VelocityEngine velocityEngine;

    private JavaMailSender mailSender;       
    
    private boolean disabled = false;
    
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;        
    }
   
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    public List<MimeMessage> send(String to, String from, String subject, String velocityTemplate,
            Map params) throws MessagingException {
                
        if (to == null || to.isEmpty()) {
            throw new IllegalArgumentException("You cannot send an email without recipients");
        }        
        
        if (from == null || from.isEmpty()) {
            throw new IllegalArgumentException("You cannot send an email without a sender");
        }
        
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("You cannot send an email without a subject");
        }
        
         MimeMessage message = createMessage(to, from, subject, velocityTemplate, params);
         List<MimeMessage> messagesNotSent = new ArrayList<MimeMessage>(1);
         
         if (disabled) return messagesNotSent;
         
         try {
             mailSender.send(message);
         } catch (Exception e) {
             log.error("Error sending Email to:" + to, e);
             messagesNotSent.add(message);
         }         
         
         return messagesNotSent;
    }
    
    public List<MimeMessage> send(String[] to, String from, String subject, String velocityTemplate,
            Map[] params) throws MessagingException {                
        if (to == null || to.length <= 0) {
            throw new IllegalArgumentException("You cannot send an email without recipients");
        }        
        
        if (from == null || from.isEmpty()) {
            throw new IllegalArgumentException("You cannot send an email without a sender");
        }
        
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("You cannot send an email without a subject");
        }
        
        if (params == null || params.length <= 0) {
            params = new Map[1];
            params[0] = new HashMap();
        }
        
        List<MimeMessage> messagesNotSent = new ArrayList<MimeMessage>(to.length);
        
        MimeMessage[] messagesToSend = new MimeMessage[to.length];
        
        if (disabled) return messagesNotSent;
        
        for (int i = 0; i < to.length; i++) {
            Map param = i < params.length ? params[i]
                    : params[params.length - 1];
            messagesToSend[i] = createMessage(to[i], from, subject,
                    velocityTemplate, param);
        }
        
        for (MimeMessage message : messagesToSend) {
            try {
                mailSender.send(message);
            } catch (Exception e) {
                log.error("Error sending Email to:" + message.toString(), e);
                messagesNotSent.add(message);
            }
        }
        
        return messagesNotSent;
    }
    
    public List<MimeMessage> send(String[] to, String from,
            String subject, String content) throws MessagingException {
        
        if (to == null || to.length <= 0) {
            throw new IllegalArgumentException("You cannot send an email without recipients");
        }
        
        if (from == null || from.isEmpty()) {
            throw new IllegalArgumentException("You cannot send an email without a sender");
        }
        
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("You cannot send an email without a subject");
        }
        
        content = content != null? content : "";
        
        // Construct the message
        MimeMessage[] messagesToSend = new MimeMessage[to.length];
        
        for (int i = 0; i < to.length; i++) {           
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);                                                          
            helper.setTo(to[i]);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content, true);
            messagesToSend[i] = message;
        }
                            
        List<MimeMessage> messagesNotSent = new ArrayList<MimeMessage>(
                messagesToSend.length);

        if (disabled) return messagesNotSent;
        
        for (MimeMessage message : messagesToSend) {
            try {
                this.mailSender.send(message);
            } catch (Exception e) {
                log.error("Error sending Email to:" + message.toString(), e);
                messagesNotSent.add(message);
            }
        }
        
        return messagesNotSent;
    }
    
    private MimeMessage createMessage(String to, String from,  String subject, String velocityTemplate,
            Map params) throws MessagingException {
        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mime);               
        message.setTo(to);
        message.setFrom(from);         
        message.setSubject(subject);                        
        String text = velocityTemplate != null && velocityTemplate.isEmpty()? VelocityEngineUtils.mergeTemplateIntoString(
           velocityEngine, velocityTemplate, params) : "";
        message.setText(text, true);
        
        return mime;
    }
            
}
