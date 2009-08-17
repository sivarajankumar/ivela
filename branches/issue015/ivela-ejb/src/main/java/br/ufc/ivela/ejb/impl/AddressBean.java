/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Address;
import br.ufc.ivela.ejb.interfaces.AddressRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigo
 */
@Stateless(mappedName="AddressBean")
public class AddressBean implements AddressRemote {

    private GenericDao<Address> daoAddress = DaoFactory.getInstance(Address.class);

    public List<Address> getAll() {
        return daoAddress.getAll();
    }
    
    public Address getByProfile(Long idProfile){
        List<Address> la = daoAddress.find("from Address where profileId = ?",new Object[]{idProfile});
        if(la!=null && la.size()>0){
            return la.get(0);
        }
        return null;
    
    }

    public Long add(Address address) {
        return (Long) daoAddress.save(address);
    }

    public Address get(Long Id) {
        return daoAddress.get(Id);
    }

    public boolean remove(Long Id) {
        return daoAddress.remove(Id);
    }

    public boolean update(Address address) {
        return daoAddress.update(address);
    }
    
    public Long lastId(){
        String sql = "select max(a.id) from Address a";
        Object params[] = new Object[]{}; 
        List<Long> list = daoAddress.find(sql, params);
        if(list!=null && list.size()!=0 && list.get(0)!=null)
            return list.get(0);
        return new Long(0);
      
    }
}
