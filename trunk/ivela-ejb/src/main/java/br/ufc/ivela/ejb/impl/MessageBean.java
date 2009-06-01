/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Message;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.ejb.interfaces.MessageRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class of ejb which implements the interface MessageLocal
 */
@Stateless(mappedName="MessageBean")
public class MessageBean implements MessageRemote {
    
    private GenericDao<Message> daoMessage = DaoFactory.getInstance(Message.class);

    public boolean remove(Long id) {
        return daoMessage.remove(daoMessage.get(id));
    }

    public Message get(Long id) {
        return daoMessage.get(id);
    }

    public Long add(Message message) {
        return (Long) daoMessage.save(message);
    }

    public List<Message> getAll() {
        return daoMessage.getAll();
    }

    public List<Message> getBySystemUserRecipient(Long systemUserId) {
        return daoMessage.getByFK("recipient.id", systemUserId);
    }

    public List<Message> getBySystemUserSender(Long systemUserId) {
        return daoMessage.getByFK("sender.id", systemUserId);
    }

    public boolean update(Message message) {
        return daoMessage.update(message);
    }
    
    public Page getBySystemUserPageRecipient(Long systemUser, String title, int page, int pageSize) {
        if (page == 0)
            page = 1;
        if (title == null)
            title = "";
        title = "%" + title + "%";
        Object[] params = new Object[] { systemUser, title };
        String countQuery = "select count(id) from Message m where m.recipient.id = ? and m.title LIKE ?";
        String query = "from Message m where m.recipient.id = ? and m.title LIKE ? order by m.datetime desc";
        
        Page p = new Page(query, countQuery, params, params, page, pageSize);
               
        return p;
    }

    public Page getBySystemUserPageSender(Long systemUser, String title, int page, int pageSize) {
        if (page == 0)
            page = 1;
        if (title == null)
            title = "";
        title = "%" + title + "%";
        Object[] params = new Object[] { systemUser, title };
        String countQuery = "select count(id) from Message m where m.sender.id = ? and m.title LIKE ?";
        String query = "from Message m where m.sender.id = ? and m.title LIKE ? order by m.datetime desc";
        
        Page p = new Page(query, countQuery, params, params, page, pageSize);
               
        return p;
    }

    public List<Message> getLastMessagesBySystemUserRecipient(Long systemUserId) {
        return daoMessage.find("from Message m where m.recipient.id = ? order by m.datetime desc", new Object[] { systemUserId });
    }

    public List<Message> getBySystemUserSender(Long systemUser, String title, int page, int pageSize) {
        if (page == 0)
            page = 1;
        if (title == null)
            title = "";
        title = "%" + title + "%";
        Object[] params = new Object[] { systemUser, title };
        String query = "from Message m where m.sender.id = ? and m.title LIKE ? order by m.datetime desc";
        List<Message> list = daoMessage.paginatedFind(query, params, pageSize, page);
        return list;
    }

    public int getBySystemUserSenderSize(Long systemUser, String title, int page, int pageSize) {
        if (page == 0)
            page = 1;
        if (title == null)
            title = "";
        title = "%" + title + "%";
        Object[] params = new Object[] { systemUser, title };
        String query = "select count(id) from Message m where m.sender.id = ? and m.title LIKE ?";
        int result = daoMessage.getCount(query, params);
        return result;
    }

    public List<Message> getBySystemUserRecipient(Long systemUser, String title, int page, int pageSize) {
        if (page == 0)
            page = 1;
        if (title == null)
            title = "";
        title = "%" + title + "%";
        Object[] params = new Object[] { systemUser, title };
        String query = "from Message m where m.recipient.id = ? and m.title LIKE ? order by m.datetime desc";
        List<Message> list = daoMessage.paginatedFind(query, params, pageSize, page);
        return list;
    }
    
    public int getBySystemUserRecipientSize(Long systemUser, String title, int page, int pageSize) {
        if (page == 0)
            page = 1;
        if (title == null)
            title = "";
        title = "%" + title + "%";
        Object[] params = new Object[] { systemUser, title };
        String query = "select count(id) from Message m where m.recipient.id = ? and m.title LIKE ?";
        int result = daoMessage.getCount(query, params);
        return result;
    }    

    
}
