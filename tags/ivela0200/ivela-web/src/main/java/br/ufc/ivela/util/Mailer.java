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
# File: Mailer.java                                                                         #
# Document: Mailer Service for the Actions                                                  # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 23-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Mail                    #
#############################################################################################
 */
package br.ufc.ivela.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.timer.TimerTaskExecutor;

import br.ufc.ivela.commons.mail.MailService;
import br.ufc.ivela.commons.model.DelayedMail;
import br.ufc.ivela.commons.model.Subscription;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.DelayedMailRemote;
import br.ufc.ivela.ejb.interfaces.SubscriptionRemote;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.TextProviderFactory;
import com.opensymphony.xwork2.util.ValueStack;

public class Mailer implements TextProvider, LocaleProvider {

    private static final long DEFAULT_TIMER = 60000;

    private static final String DEFAULT_SENDER = "ivela@ivela.org";
    
    protected Log log = LogFactory.getLog(getClass());

    private MailService mailService;
    
    private SubscriptionRemote subscriptionRemote;

    private DelayedMailRemote delayedMailRemote;

    private final TextProvider textProvider = new TextProviderFactory()
            .createInstance(getClass(), this);

    private TaskExecutor taskExecutor;

    private TimerTaskExecutor scheduledExecutor = new TimerTaskExecutor();

    private static Boolean workRunning = false;

    private long delayTime = DEFAULT_TIMER;
    
    private String sender = DEFAULT_SENDER;    
    
    /**
     * Subscribe to receive alerts for a specific Model.
     * 
     * @param to Model object that you wish to subscribe to.
     * @param type The type of subscription
     * @param user The user that is subscribing for the object
     * @param email Use this parameter if you want to override email for the user
     */
    public void subscribeTo(Object to, String type, SystemUser user,
            String email) {
        Subscription subscription = subscriptionRemote.createSubscription(to, type, user, email);
        subscriptionRemote.add(subscription);
    }

    /**
     * Subscribe to receive alerts for a specific Model.
     * 
     * @param to Model object that you wish to subscribe to.
     * @param type The type of subscription
     * @param user The user that is subscribing for the object
     */
    public void subscribeTo(Object to, String type, SystemUser user) {
        subscribeTo(to, type, user, null);
    }

    /**
     * Unsubscribe the User from a specific model
     *  
     * @param from Model object that you wish to unsubscribe from.
     * @param type The type of subscription
     * @param user The user that is to be removed from the subscriptions
     */
    public void unsubscribe(Object from, String type, SystemUser user) {
        Subscription subscription = subscriptionRemote.createSubscription(from, type, user, null);
        Collection<Subscription> subscriptions = subscriptionRemote
                .getAll(subscription);
        subscriptionRemote.remove(subscriptions);
    }

    /**
     * Send an email based on a Model object
     * 
     * @param origin The model from which the subscriptions will be retrieved.
     * @param type The Type of the subscription
     * @param params Additional Parameters to be used in the Velocity Template
     * @param from 
     * @param retry
     */
    public void send(Object origin, String type, Map params, String from, boolean retry) {                
        MailSender sender = new MailSenderVelocity(origin, type, from, params);
        sender.setRetry(retry);
        taskExecutor.execute(sender);
    }
   
    /**
     * Send an email based on a Velocity Template
     * @param to
     * @param from
     * @param subject
     * @param velocityTemplate
     * @param params
     * @param retry
     */
    public void send(SystemUser[] to, String from, String subject, String velocityTemplate, Map[] params, boolean retry) {
        String[] toString;
        
        Map[] parameters = null;
        if (to == null || to.length <= 0) {
            toString = new String[0];
        } else {
            toString = new String[to.length];
            parameters = new Map[to.length];
            for (int i = 0; i < to.length; i++) {
                toString[i] = to[i].getEmail();
                parameters[i] = new HashMap();
                parameters[i].put(filterClass(to[i]), to[i]);
            }
            
            if (params != null) {
                for (int i = 0, j=0; i < params.length && j < parameters.length; i++, j++) {
                    parameters[j].putAll(params[i]);
                }
            }
        }
         
        subject = getText(subject);
        velocityTemplate = getText(velocityTemplate);
        
        parameters = parameters != null && parameters.length > 0? parameters : params;
        
        MailSender sender = new MailSenderVelocity(toString, from, subject, velocityTemplate, parameters);
        sender.setRetry(retry);
        taskExecutor.execute(sender);
    }    
    
    /**
     * Send an email based on a Velocity Template
     * @param to
     * @param from
     * @param subject
     * @param velocityTemplate
     * @param params
     * @param retry
     */
    public void send(String[] to, String from, String subject, String velocityTemplate, Map[] params, boolean retry) {
        subject = getText(subject);
        velocityTemplate = getText(velocityTemplate);
        MailSender sender = new MailSenderVelocity(to, from, subject, velocityTemplate, params);
        sender.setRetry(retry);
        taskExecutor.execute(sender);
    }

    /**
     * Send an email
     * 
     * @param to
     * @param from
     * @param subject
     * @param content
     * @param retry
     */
    public void send(String[] to, String from, String subject, String content, boolean retry) {
        subject = getText(subject);
        content = getText(content);
        MailSender sender = new MailSenderText(to, from, subject, content);
        sender.setRetry(retry);
        taskExecutor.execute(sender);
    }

    /**
     * Send an email synchronously, any exception that occurs will be propagated
     * 
     * @param to
     * @param from
     * @param subject
     * @param content
     * @param retry
     */
    public void sendSynchronously(String[] to, String from, String subject, String content, boolean retry) {
        subject = getText(subject);
        content = getText(content);
        MailSender sender = new MailSenderText(to, from, subject, content);
        sender.setRetry(retry);
        sender.setPropagateExceptions(true);
        sender.run();
    }
    
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public String getText(String aTextName) {
        try {
            return textProvider.getText(aTextName); 
        } catch (Exception e) {
            return aTextName;
        }
    }

    public String getText(String aTextName, String defaultValue) {
        try {
            return textProvider.getText(aTextName, defaultValue);
        } catch (Exception e) {
            return aTextName;
        }
    }

    public String getText(String aTextName, String defaultValue, String obj) {
        try {
            return textProvider.getText(aTextName, defaultValue, obj);
        } catch (Exception e) {
            return aTextName;
        }
    }

    public String getText(String aTextName, List args) {
        try {
            return textProvider.getText(aTextName, args);
        } catch (Exception e) {
            return aTextName;
        }
    }

    public String getText(String key, String[] args) {
        try {
            return textProvider.getText(key, args);
        } catch (Exception e) {
            return key;
        }
    }

    public String getText(String aTextName, String defaultValue, List args) {
        try {
            return textProvider.getText(aTextName, defaultValue, args);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getText(String key, String defaultValue, String[] args) {
        try {
            return textProvider.getText(key, defaultValue, args);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getText(String key, String defaultValue, List args,
            ValueStack stack) {
        try {
            return textProvider.getText(key, defaultValue, args, stack);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getText(String key, String defaultValue, String[] args,
            ValueStack stack) {
        try {
            return textProvider.getText(key, defaultValue, args, stack);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public ResourceBundle getTexts() {
        return textProvider.getTexts();
    }

    public ResourceBundle getTexts(String aBundleName) {
        return textProvider.getTexts(aBundleName);
    }

    public Locale getLocale() {
        return ActionContext.getContext().getLocale();
    }

    public void setSubscriptionRemote(SubscriptionRemote subscriptionRemote) {
        this.subscriptionRemote = subscriptionRemote;
    }

    public SubscriptionRemote getSubscriptionRemote() {
        return subscriptionRemote;
    }

    public void setDelayedMailRemote(DelayedMailRemote delayedMailRemote) {
        this.delayedMailRemote = delayedMailRemote;
    }

    public DelayedMailRemote getDelayedMailRemote() {
        return delayedMailRemote;
    }

    public void setDelayTime(long timer) {
        this.delayTime = timer;
    }

    public long getDelayTime() {
        return delayTime;
    }
    
    public void setSender(String sender) {
        if (sender != null && !sender.isEmpty())
            this.sender = sender;
    }
    
    private String filterClass(Object object) {
        String className = object.getClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1,
                className.length());
        return className;
    }
    
    private abstract class MailSender implements Runnable {    	

        protected String from;
        
        private MailSender(){ }                
        
        private boolean retry = true;
        
        private boolean propagate = false;

        public void setRetry(boolean retry) {
        	this.retry = retry;
        }
        
        public void setPropagateExceptions(boolean propagate) {
            this.propagate = propagate;
        }
        
    	public void run() {
    	    List<MimeMessage> messagesNotSent = null;
    	    try {
    	        messagesNotSent = sendMessages();
    	    } catch (Exception e) {
    	        if (propagate) throw new MailSendException("Could not send email", e);
    	    }

    		if (retry && messagesNotSent != null && !messagesNotSent.isEmpty()) {
    		    log.warn("Emails have not been sent");
    			for (MimeMessage message : messagesNotSent) {
    				StringBuilder builder = new StringBuilder();
    				builder.append("Message: ");    				
    				try {
                        DelayedMail delayedMail = new DelayedMail();
                        String recipient = null;
                        String sender = null;
                        String subject = null;
                        try {
                            if (!(message.getContent() instanceof String)) {
                                continue;
                            }
                            delayedMail
                                    .setBody(message.getContent().toString());
                            Address[] addresses = message
                                    .getRecipients(RecipientType.TO);
                            if (addresses == null || addresses.length <= 0)
                                continue;
                            
                            recipient = addresses[0].toString();
                            delayedMail.setRecipient(recipient);
                            addresses = message.getFrom();
                            if (addresses == null || addresses.length <= 0)
                                sender = DEFAULT_SENDER;
                            else
                                sender = message.getFrom()[0].toString();                            
                            delayedMail.setSender(sender);                               
                            subject = message.getSubject();
                            delayedMail.setSubject(subject);
                            builder.append(recipient);
                            builder.append(':');
                            builder.append(sender);
                            builder.append(':');
                            builder.append(subject);
                            log.warn(builder.toString());
                        } catch (Exception e) {
                            log.error("Error creating delayed email:" + recipient + ' ' + subject, e);
                        }

    					delayedMailRemote.add(delayedMail);    				    				   
    				} catch (Exception e) {
    				    log.error("Error creating delayed emails to send later", e);    				    
    				}    				    			
    			}
    			
    			synchronized (workRunning) {
    				if (!workRunning) {
    					log.debug("Starting Mail Background Worker");
    					TimerTask task = new MailWorker();
    					Timer timer = new Timer();
    					timer.schedule(task, delayTime, delayTime);
    					scheduledExecutor.setTimer(timer);
    					scheduledExecutor.execute(task);
    				}
    			}

    			if (propagate) {
    			    throw new MailSendException("Could not send emails, retrying...");
    			}
    		}
    	}

        public abstract List<MimeMessage> sendMessages();
    }
    
    private class MailSenderText extends MailSender {
    	private String[] to;    	
    	private String subject;
    	private String content;

    	    	MailSenderText(String[] to, String from, String subject, String content) {
    		this.to = to;
    		this.from = from != null && !from.isEmpty()? from : sender;
    		this.subject = subject;
    		this.content = content;
    	}

    	public List<MimeMessage> sendMessages() {
    		List<MimeMessage> messagesNotSent = null;
    		try {
    			messagesNotSent = mailService.send(to, from, subject, content);
    		} catch (MessagingException e) {
    			log.error("Could not create mail to send: " + to[to.length - 1]
    					+ ' ' + subject, e);
    			throw new IllegalArgumentException("Error creating emails to Send", e);
    		}
    		return messagesNotSent;
    	}

    }
    
    private class MailSenderVelocity extends MailSender {
        
        private static final String DEFAULT_VELOCITY_LOCATION = "template/velocity/";
    	private String[] to;    	
    	private String subject;
    	private String velocityTemplate;
    	private Map[] params;
    	
    	private Object origin;
    	private String type;
    	private Map param;
    	private String velLocation = DEFAULT_VELOCITY_LOCATION;
    	
    	MailSenderVelocity(String[] to, String from, String subject, String velocityTemplate, Map[] params) {
    		this.to = to;
    		this.from = from != null && !from.isEmpty()? from : sender;
    		this.subject = subject;
    		this.velocityTemplate = velocityTemplate;
    		this.params = params;
    	}
    	
    	MailSenderVelocity(Object origin, String type, String from, Map param) {    		
    	    this.origin = origin;
    	    this.type = type;
    	    this.from = from != null && !from.isEmpty()? from : sender;
    	    this.param = param;
    	}
    	
    	public void setVelocityTemplateLocation(String location) {
    	    this.velLocation = location;
    	}
    	
    	public List<MimeMessage> sendMessages() {
    	    
    	    List<MimeMessage> messagesNotSent = null;
    	    
    	    if (origin != null) {
    	        List<Subscription> subscriptions = null;
    	        
    	        if (type == null || type.isEmpty()) {
    	            subscriptions = subscriptionRemote.getSubscriptionByClass(origin);
    	        } else {
    	            subscriptions = subscriptionRemote.getSubscriptionByClassAndType(origin, type);
    	        }
    	        
    	        params = new Map[subscriptions.size()];
    	        
    	        if (subscriptions.isEmpty()) {
    	            log.info("No subscription found for: " + origin.toString());
    	            return messagesNotSent;
    	        }
    	        
    	        Subscription subscript = subscriptions.get(0);
    	        
    	        velocityTemplate = subscript.getCategory() + '_' + subscript.getType() + '_' + getLocale().getLanguage() + ".vm";
    	        subject = getText(subscript.getCategory() + '.' + subscript.getType());
    	        
    	        for (int i = 0; i < subscriptions.size(); i++) {
    	            Map map = new HashMap(param);
    	            map.put(filterClass(subscriptions.get(i).getUser()), subscriptions.get(i).getUser());
    	            params[i] = map;    	            
    	        }    	            	        
    	        
    	    }
    	        		
    	    velocityTemplate = velLocation + velocityTemplate;
    	    
    		try {
    			messagesNotSent = mailService.send(to, from, subject, velocityTemplate, params);
    		} catch (Exception e) {
    			log.error("Could not create mail to send: " + to[to.length - 1]
    					+ ' ' + subject, e);
    			throw new IllegalArgumentException("Error creating emails to Send", e);
    		}
    		return messagesNotSent;
    	}
    }
    
    
    private class MailWorker extends TimerTask {
        private Log log = LogFactory.getLog(getClass());
        
        @Override
        public void run() {
            Collection<DelayedMail> delayedMails = delayedMailRemote.getAll();
            List<DelayedMail> delayedMailsToDel = new ArrayList<DelayedMail>();
            for (DelayedMail delayedMail: delayedMails) {
                List<MimeMessage> messagesNotSent;
                try {
                    messagesNotSent = mailService
                            .send(new String[]{delayedMail.getRecipient()}, delayedMail
                                    .getSender(), delayedMail.getSubject(),
                                    delayedMail.getBody());
                } catch (Exception e) {
                    log.error("Could not send email in the retry: " + delayedMail.getRecipient() + ":" + delayedMail.getSubject());
                                        
                    return;
                }   
                
                if (messagesNotSent != null && !messagesNotSent.isEmpty()) {                    
                    if (delayedMail.getRetries() > 10) {
                        delayedMailsToDel.add(delayedMail);
                        log.warn("Could not send email after 10 retries:"
                                + delayedMail.getRecipient() + ":"
                                + delayedMail.getSubject());
                    } else {
                        delayedMail.setRetries(delayedMail.getRetries() + 1);
                        delayedMailRemote.update(delayedMail);
                    }
                } else {
                    delayedMailsToDel.add(delayedMail);
                }
            }
            
            delayedMailRemote.remove(delayedMailsToDel);
        }
    }
}
