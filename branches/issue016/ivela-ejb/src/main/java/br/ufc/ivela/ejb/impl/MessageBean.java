/*    
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
# File: MessageBean.java                                                                    #
# Document: Class of ejb which implements the interface MessageLocal                        # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - Leonardo Oliveira Moreira         - XXXXXX - Initial Version                #
# 15-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
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
        String query = "from Message m where m.recipient.id = ? and m.title LIKE ? and m.recipientDeleted = false order by m.datetime desc";
        
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
        String query = "from Message m where m.sender.id = ? and m.title LIKE ? and m.senderDeleted = false order by m.datetime desc";
        
        Page p = new Page(query, countQuery, params, params, page, pageSize);
               
        return p;
    }

    public List<Message> getLastMessagesBySystemUserRecipient(Long systemUserId) {
        return daoMessage.find("from Message m where m.recipient.id = ? and m.recipientDeleted = false order by m.datetime desc", new Object[] { systemUserId });
    }

    public List<Message> getBySystemUserSender(Long systemUser, String title, int page, int pageSize) {
        if (page == 0)
            page = 1;
        if (title == null)
            title = "";
        title = "%" + title + "%";
        Object[] params = new Object[] { systemUser, title };
        String query = "from Message m where m.sender.id = ? and m.title LIKE ? and m.senderDeleted = false order by m.datetime desc";
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
        String query = "select count(id) from Message m where m.sender.id = ? and m.title LIKE ? and m.senderDeleted = false";
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
        String query = "from Message m where m.recipient.id = ? and m.title LIKE ? and m.recipientDeleted = false order by m.datetime desc";
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
        String query = "select count(id) from Message m where m.recipient.id = ? and m.title LIKE ? and m.recipientDeleted = false ";
        int result = daoMessage.getCount(query, params);
        return result;
    }    

    
}
