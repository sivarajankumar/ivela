/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Phone;
import br.ufc.ivela.ejb.interfaces.PhoneRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigo
 */
@Stateless(mappedName="PhoneBean")
public class PhoneBean implements PhoneRemote {

    private GenericDao<Phone> daoPhone = DaoFactory.getInstance(Phone.class);

    public List<Phone> getAll() {
        return daoPhone.getAll();
    }

    public Long add(Phone phone) {
        return (Long) daoPhone.save(phone);
    }

    public Phone get(Long Id) {
        return daoPhone.get(Id);
    }

    public boolean remove(Long Id) {
        return daoPhone.remove(Id);
    }

    public boolean update(Phone phone) {
        return daoPhone.update(phone);
    }
    
    public List<Phone> getByProfile(Long idProfile){
        return daoPhone.find("from Phone p where p.profileId = ? order by p.id", new Object[]{idProfile});
    }
    
    public Long lastId(){
        String sql = "select max(p.id) from Phone p";
        Object params[] = new Object[]{};
        List<Long> list = daoPhone.find(sql, params);
        if(list!=null && list.size()!=0 && list.get(0)!=null)
            return list.get(0);
        return new Long(0);
      
    }
}
