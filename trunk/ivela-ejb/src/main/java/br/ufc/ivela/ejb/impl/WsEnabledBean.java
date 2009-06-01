/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.WsEnabled;
import br.ufc.ivela.ejb.interfaces.WsEnabledRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class of ejb which implements the interface WsEnabledLocal
 */
@Stateless(mappedName = "WsEnabledBean")
public class WsEnabledBean implements WsEnabledRemote {

    private GenericDao<WsEnabled> daoWsEnabled = DaoFactory.getInstance(WsEnabled.class);

    public WsEnabled get(Long id) {
        if (id == null) {
            return null;
        }
        return daoWsEnabled.get(id);
    }

    public Long add(WsEnabled wsEnabled) {
        return (Long) daoWsEnabled.save(wsEnabled);
    }

    public boolean remove(Long id) {
        return daoWsEnabled.remove(id);
    }

    public List<WsEnabled> getAll() {
        return daoWsEnabled.getAll();
    }

    public boolean ipEnabled(String ip) {
        List<WsEnabled> list = daoWsEnabled.getAll();
        for (WsEnabled ws : list) {
            if (ws.getIp().equalsIgnoreCase(ip)) {
                return true;
            }
        }
        return false;
    }

}
