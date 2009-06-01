/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Logger;
import br.ufc.ivela.commons.model.History;
import br.ufc.ivela.commons.model.HistoryParams;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.HistoryParamsRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

/**
 *
 * @author marcus
 */
public abstract class GenericAction extends ActionSupport {

    protected Logger logger;
    private HistoryRemote historyRemote;
    private HistoryParamsRemote historyParamsRemote;

    public HistoryParamsRemote getHistoryParamsRemote() {
        return historyParamsRemote;
    }

    public void setHistoryParamsRemote(HistoryParamsRemote historyParamsRemote) {
        this.historyParamsRemote = historyParamsRemote;
    }

    public HistoryRemote getHistoryRemote() {
        return historyRemote;
    }

    public void setHistoryRemote(HistoryRemote historyRemote) {
        this.historyRemote = historyRemote;
    }

    public SystemUser getAuthenticatedUser() {

        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (obj != null) {
            if (obj instanceof UserDetails) {
                return (SystemUser) obj;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean isUserLogged() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (obj != null) {
            if (obj instanceof UserDetails) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Retrieves the session
     * @return a session
     */
    public Map getSession() {
        return ActionContext.getContext().getSession();
    }

    /**
     * Retrieves the logger
     * @return logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Sets the logger 
     * @param logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void addHistory(String title, String description, String... params) {
        if (historyRemote == null) {
            try {
                InitialContext initialContext = new InitialContext();
                java.lang.Object ejbRemoteRef = initialContext.lookup("HistoryBean#br.ufc.ivela.ejb.interfaces.HistoryRemote");
                historyRemote = (HistoryRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, HistoryRemote.class);
            } catch (NamingException e) {
                e.printStackTrace();
                historyRemote = null;
            }
        }

        List<History> historyList = historyRemote.getBySystemUser(getAuthenticatedUser().getId());
        boolean insert = true;
        for (History h : historyList) {
            if (h.getDescription().equalsIgnoreCase(description) && h.getTitle().equalsIgnoreCase(title)) {
                List<HistoryParams> historyParamsList = historyParamsRemote.getByHistory(h.getId());
                int equal = 0;
                for (int i = 0; i < params.length; i++) {
                    for (HistoryParams hp : historyParamsList) {
                        if (hp.getParam().equalsIgnoreCase(String.valueOf(i)) && hp.getValue().equalsIgnoreCase(params[i])) {
                            equal++;
                            break;
                        }
                    }
                }
                if (equal == params.length) {
                    insert = false;
                    break;
                }
            }
        }
        if (insert) {
            History history = new History();
            history.setSystemUser(getAuthenticatedUser());
            history.setDatetime(new Date());
            history.setTitle(title);
            history.setDescription(description);
            Long id = historyRemote.add(history);
            for (int i = 0; params != null && i < params.length; i++) {
                HistoryParams historyParams = new HistoryParams();
                historyParams.setHistoryId(id);
                historyParams.setParam(String.valueOf(i));
                historyParams.setValue(params[i]);
                historyParamsRemote.add(historyParams);
            }
        }
    }

    public void addHistory(String title, String description, SystemUser systemUser, String... params) {
        if (historyRemote == null) {
            try {
                InitialContext initialContext = new InitialContext();
                java.lang.Object ejbRemoteRef = initialContext.lookup("HistoryBean#br.ufc.ivela.ejb.interfaces.HistoryRemote");
                historyRemote = (HistoryRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, HistoryRemote.class);
            } catch (NamingException e) {
                e.printStackTrace();
                historyRemote = null;
            }
        }
        List<History> historyList = historyRemote.getBySystemUser(systemUser.getId());
        boolean insert = true;
        for (History h : historyList) {
            if (h.getDescription().equalsIgnoreCase(description) && h.getTitle().equalsIgnoreCase(title)) {
                List<HistoryParams> historyParamsList = historyParamsRemote.getByHistory(h.getId());
                int equal = 0;
                for (int i = 0; i < params.length; i++) {
                    for (HistoryParams hp : historyParamsList) {
                        if (hp.getParam().equalsIgnoreCase(String.valueOf(i)) && hp.getValue().equalsIgnoreCase(params[i])) {
                            equal++;
                            break;
                        }
                    }
                }
                if (equal == params.length) {
                    insert = false;
                    break;
                }
            }
        }
        if (insert) {
            History history = new History();
            history.setSystemUser(systemUser);
            history.setDatetime(new Date());
            history.setTitle(title);
            history.setDescription(description);
            Long id = historyRemote.add(history);
            for (int i = 0; params != null && i < params.length; i++) {
                HistoryParams historyParams = new HistoryParams();
                historyParams.setHistoryId(id);
                historyParams.setParam(String.valueOf(i));
                historyParams.setValue(params[i]);
                historyParamsRemote.add(historyParams);
            }
        }
    }
}
