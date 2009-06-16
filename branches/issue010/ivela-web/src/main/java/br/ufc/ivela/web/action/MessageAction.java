/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Message;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.MessageRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import org.springframework.util.StringUtils;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Action of the message system
 */
public class MessageAction extends GenericAction {

    private MessageRemote messageRemote;
    private SystemUserRemote systemUserRemote;
    private Message message;
    private List<Message> messageList;
    private List<SystemUser> systemUserList;
    private InputStream inputStream;
    private int pageCount;
    private int page;
    private int pageSize = 5;
    private int count;
    private String messageTitle;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Add a new message, get the authenticated user and the date, and if was read.
     * Then the validate method check if there error.
     * If there isn't the inbox method is called, if there erro, the input method is called
     */
    public String add() {
        message.setSender(getAuthenticatedUser());
        message.setDatetime(new Date());
        message.setRead(false);
        performValidateAdd();
        if (!hasActionErrors()) {
            Long result = messageRemote.add(message);
            if (result != null) {
                return inbox();
            }
        }
        return input();
    }

    /**
     * Send a message
     * @return json
     */
    public String sendMessage() {
        message.setSender(getAuthenticatedUser());
        message.setDatetime(new Date());
        message.setRead(false);
        performValidateAdd();
        if (!hasActionErrors()) {
            Long result = messageRemote.add(message);
            if (result != null) {
                XStream xStream = new XStream(new JettisonMappedXmlDriver());
                xStream.alias("messages", String.class);
                setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
                return "json";
            }

        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("messages", String.class);
        setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
        return "json";
    }

    /**
     * 
     * @return
     */
    public String replyMessage() {
        message.setSender(getAuthenticatedUser());
        message.setDatetime(new Date());
        message.setRead(false);
        performValidateAdd();
        if (!hasActionErrors()) {
            Long result = messageRemote.add(message);
            if (result != null) {
                XStream xStream = new XStream(new JettisonMappedXmlDriver());
                xStream.alias("messages", String.class);
                setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
                return "json";
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("messages", String.class);
        setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
        return "json";
    }

    /**
     * Add a new message
     */
    public String reply() {
        message.setSender(getAuthenticatedUser());
        message.setDatetime(new Date());
        message.setRead(false);
        performValidateAdd();
        if (!hasActionErrors()) {
            Long result = messageRemote.add(message);
            if (result != null) {
                return searchInbox();
            }
        }
        return searchInbox();
    }

    /**
     * Remove a message
     */
    public String removeInbox() {
        performValidateRemove();
        if (!hasActionErrors()) {
            boolean result = messageRemote.remove(message.getId());
            if (result) {
                return inbox();
            }
        }
        return inbox();
    }

    /**
     * 
     * @return
     */
    public String removeInboxMessage() {
        performValidateRemove();
        if (!hasActionErrors()) {
            boolean result = messageRemote.remove(message.getId());
            if (result) {
                XStream xStream = new XStream(new JettisonMappedXmlDriver());
                xStream.alias("messages", String.class);
                setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
                return "json";
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("messages", String.class);
        setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
        return "json";
    }

    /**
     * Remove a message
     */
    public String removeOutbox() {
        performValidateRemove();
        if (!hasActionErrors()) {
            boolean result = messageRemote.remove(message.getId());
            if (result) {
                return outbox();
            }
        }
        return outbox();
    }

    /**
     * 
     * @return
     */
    public String removeOutboxMessage() {
        performValidateRemove();
        if (!hasActionErrors()) {
            boolean result = messageRemote.remove(message.getId());
            if (result) {
                XStream xStream = new XStream(new JettisonMappedXmlDriver());
                xStream.alias("messages", String.class);
                setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
                return "json";
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("messages", String.class);
        setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
        return "json";
    }

    /**
     * Update a message
     * 
     * @return inbox
     */
    public String update() {
        return inbox();
    }

    /**
     * 
     * @return
     */
    public String inboxLastMessages() {
        if (page < 1) {
            page = 1;
        }
        if (messageTitle == null) {
            messageTitle = "";
        }
        messageList = messageRemote.getBySystemUserRecipient(getAuthenticatedUser().getId(), messageTitle, page, pageSize);

        setCount(messageRemote.getBySystemUserRecipientSize(getAuthenticatedUser().getId(), messageTitle, page, pageSize));
        setPageCount(messageList.size());

        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        if (!messageList.isEmpty()) {
            xStream.alias("messages", List.class);

            xStream.alias("message", Message.class);
            xStream.omitField(SystemUser.class, "profile");
            xStream.omitField(SystemUser.class, "functionalities");
            xStream.omitField(SystemUser.class, "authentications");
            xStream.omitField(Profile.class, "phones");
            xStream.omitField(Profile.class, "addresses");

            setInputStream(new ByteArrayInputStream(xStream.toXML(messageList).getBytes()));
        } else {
            xStream.alias("messages", String.class);
            setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
        }

        return "json";

    }

    /**
     * 
     * @return
     */
    public String outboxLastMessages() {
        if (page < 1) {
            page = 1;
        }
        if (messageTitle == null) {
            messageTitle = "";
        }
        messageList = messageRemote.getBySystemUserSender(getAuthenticatedUser().getId(), messageTitle, page, pageSize);

        setCount(messageRemote.getBySystemUserSenderSize(getAuthenticatedUser().getId(), messageTitle, page, pageSize));
        setPageCount(messageList.size());

        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        if (!messageList.isEmpty()) {
            xStream.alias("messages", List.class);

            xStream.alias("message", Message.class);
            xStream.omitField(SystemUser.class, "profile");
            xStream.omitField(SystemUser.class, "functionalities");
            xStream.omitField(SystemUser.class, "authentications");
            xStream.omitField(Profile.class, "phones");
            xStream.omitField(Profile.class, "addresses");

            setInputStream(new ByteArrayInputStream(xStream.toXML(messageList).getBytes()));
        } else {
            xStream.alias("messages", String.class);
            setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
        }

        return "json";

    }

    /**
     * 
     * @return
     */
    public String prepareWriteMessage() {

        systemUserList = systemUserRemote.list();

        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        if (!systemUserList.isEmpty()) {
            xStream.alias("systemUsers", List.class);

            xStream.alias("systemUser", SystemUser.class);
            xStream.omitField(SystemUser.class, "email");
            xStream.omitField(SystemUser.class, "password");
            xStream.omitField(SystemUser.class, "socialNumber");
            xStream.omitField(SystemUser.class, "enabled");
            xStream.omitField(SystemUser.class, "profile");
            xStream.omitField(SystemUser.class, "functionalities");
            xStream.omitField(SystemUser.class, "authentications");
            xStream.omitField(SystemUser.class, "lastGrade");
            xStream.omitField(SystemUser.class, "lastUnitContent");

            setInputStream(new ByteArrayInputStream(xStream.toXML(systemUserList).getBytes()));
        } else {
            xStream.alias("systemUsers", String.class);
            setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
        }

        return "json";

    }

    public String searchUsers() {
        systemUserList = systemUserRemote.getUsersByUsername(username);
        String html = "";
        html += "<ul>";
        if (!systemUserList.isEmpty()) {
            for (SystemUser su : systemUserList) {
                html += "<li id=\"" + su.getId() + "\">" + su.getUsername() + "</li>";
            }
        }
        html += "</ul>";        
        setInputStream(new ByteArrayInputStream(html.getBytes()));
        return "text";
    }

    
    /**
     * Perform a validate to the add method
     */
    private void performValidateAdd() {
        if (message == null) {
            addActionError(getText("message.input.validation.required"));
        }
        // verifies if the title is empty
        if (!StringUtils.hasText(message.getTitle())) {
            addActionError(getText("message.input.validation.title"));
        }

        // verifies if the description is empty
        if (!StringUtils.hasText(message.getDescription())) {
            addActionError(getText("message.input.validation.description"));
        }

        // verifies if the recipient is empty
        if (message.getRecipient().getId() == null) {
            addActionError(getText("message.input.validation.recipient"));
        } else {
            if (systemUserRemote.get(message.getRecipient().getId()) == null) {
                addActionError(getText("message.input.validation.recipient"));
            }
        }

        // verifies if the recipient is empty
        if (message.getSender().getId() == null) {
            addActionError(getText("message.input.validation.sender"));
        } else {
            if (systemUserRemote.get(message.getSender().getId()) == null) {
                addActionError(getText("message.input.validation.sender"));
            }
        }
    }

    /**
     * Perform a validate to the remove method
     */
    private void performValidateRemove() {
        // verifies if there is an id
        if (message.getId() == null) {
            addActionError(getText("message.input.validation.requiredId"));
        } else {
            // verifies if this id is valid
            if (messageRemote.get(message.getId()) == null) {
                addActionError(getText("message.input.validation.invalidId"));
            }
        }
    }

    public String getInbox() {
        message = messageRemote.get(message.getId());
        return "showInbox";
    }

    public String getOutbox() {
        message = messageRemote.get(message.getId());
        return "showOutbox";
    }

    /**
     * Sets the variables to be used on the input message
     */
    public String input() {
        return INPUT;
    }

    /**
     * List all message
     */
    public String inbox() {
        if (page < 1) {
            page = 1;
        }
        if (messageTitle == null) {
            messageTitle = "";
        }
        Page p = messageRemote.getBySystemUserPageRecipient(getAuthenticatedUser().getId(), messageTitle, page, pageSize);
        messageList = p.getList();
        setCount(p.getCount());
        setPageCount(p.getPageCount());
        return "inbox";
    }

    /**
     * List all message
     */
    public String outbox() {
        if (page < 1) {
            page = 1;
        }
        if (messageTitle == null) {
            messageTitle = "";
        }
        Page p = messageRemote.getBySystemUserPageSender(getAuthenticatedUser().getId(), messageTitle, page, pageSize);
        messageList = p.getList();
        setCount(p.getCount());
        setPageCount(p.getPageCount());

        return "outbox";
    }

    /**
     * Retrieves the input Stream
     * @return inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets the input Stream
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Retrieves the count
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Retrieves the page count
     * @return pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Sets the page count
     * @param pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Retrieves the page Size
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page Size
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Retrieve the title of the message
     * @return messageTitle
     */
    public String getMessageTitle() {
        return messageTitle;
    }

    /**
     * Sets the title of the message
     * @param messageTitle
     */
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    /**
     * Retrieve the page
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the page
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Retrieve a list of system user
     * @return systemUserList
     */
    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    /**
     * Sets a list of system user
     * @param systemUserList
     */
    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    /**
     * Retrieve the inbox search
     * @return searchInbox
     */
    public String searchInbox() {
        return "searchInbox";
    }

    /**
     * Sets the inbox search
     * @return
     */
    public String searchOutbox() {
        return "searchOutbox";
    }

    /**
     * Retrieves the message
     * @return message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Sets the message
     * @param message
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Retrieve the list of message
     * @return messageList
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     * Sets a list of message
     * @param messageList
     */
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    /**
     * Retrieves the remote message
     * @return messageRemote
     */
    public MessageRemote getMessageRemote() {
        return messageRemote;
    }

    /**
     * Sets the remote message
     * @param messageRemote
     */
    public void setMessageRemote(MessageRemote messageRemote) {
        this.messageRemote = messageRemote;
    }

    /**
     * Retrieve the remote system user
     * @return systemUserRemote
     */
    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    /**
     * Sets the remote system user
     * @param systemUserRemote
     */
    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }
}
