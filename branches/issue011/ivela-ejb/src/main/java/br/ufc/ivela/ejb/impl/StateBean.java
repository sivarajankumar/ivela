/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.State;
import br.ufc.ivela.ejb.interfaces.StateRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigo
 */
@Stateless(mappedName="StateBean")
public class StateBean implements StateRemote {

    private GenericDao<State> daoState = DaoFactory.getInstance(State.class);

    public List<State> getAll() {
        return daoState.getAll();
    }

    public List<State> getByCountry(Long countryId){
        return daoState.getByFK("country.id", countryId);
    }
    
    public Long add(State state) {
        return (Long) daoState.save(state);
    }

    public State get(Long Id) {
        return daoState.get(Id);
    }

    public boolean remove(Long Id) {
        return daoState.remove(Id);
    }

    public boolean update(State state) {
        return daoState.update(state);
    }
    
    public Long lastId(){
        String sql = "select max(s.id) from State s";
        Object params[] = new Object[]{}; 
        List<Long> list = daoState.find(sql, params);
        if(list!=null && list.size()!=0 && list.get(0)!=null)
            return list.get(0);
        return new Long(0);
      
    }
}
