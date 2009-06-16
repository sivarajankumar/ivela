/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author marcus
 */
public class GenericDaoImpl<T> extends HibernateDaoSupport implements GenericDao<T>, Serializable {

    private Class<T> clazz;

    protected GenericDaoImpl() {
    }

    public T get(Serializable id) {
        return (T) getHibernateTemplate().get(clazz, id);
    }

    public List<T> getAll() {
        return getAll("id", ORDER_ASC);
    }

    public List<T> getAll(final String fieldToOrder, final int orderType) {
        return getLastRecords(fieldToOrder, orderType, 0);
    }
    
    public List<T> getLastRecords(final String fieldToOrder, final int orderType, final int number){
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException {
                
                Criteria criteria = session.createCriteria(clazz);
                
                if(number!=0){
                    criteria.setMaxResults(number);
                }
                
                if(orderType == ORDER_DESC) {                
                    return criteria.addOrder(Order.desc(fieldToOrder)).list();                
                } else if(orderType == ORDER_ASC){
                    return criteria.addOrder(Order.asc(fieldToOrder)).list();  
                } else {
                    return criteria.list();
                }
                
            }
        };
        return (List<T>) getHibernateTemplate().execute(callback);
    }
    
    public List<T> getByExample(final T t) {
        if (t == null) {
            return getAll();
        } else {
            HibernateCallback callback = new HibernateCallback() {

                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(t).ignoreCase();
                    return session.createCriteria(clazz).add(ex).list();
                }
            };
            return (List<T>) getHibernateTemplate().execute(callback);
        }
    }

    public List<T> getByExampleLike(final T t, final MatchMode matchMode) {
        if (t == null) {
            return getAll();
        } else {
            HibernateCallback callback = new HibernateCallback() {

                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(t).ignoreCase().enableLike(matchMode);
                    return session.createCriteria(clazz).add(ex).list();
                }
            };
            return (List<T>) getHibernateTemplate().execute(callback);
        }
    }

    public Serializable save(T t) {
        return getHibernateTemplate().save(t);
    }

    public boolean remove(T t) {
        try {
            getHibernateTemplate().delete(t);

            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(Serializable id) {
        return remove((T) getHibernateTemplate().load(clazz, id));
    }

    public boolean removeAll(Collection<T> collection) {
        try {
            getHibernateTemplate().deleteAll(collection);

            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(T t) {
        try {
            getHibernateTemplate().update(t);

            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List getByNamedQuery(String namedQuery, String[] paramNames, Object[] paramValues) {
        return getHibernateTemplate().findByNamedQueryAndNamedParam(namedQuery, paramNames, paramValues);
    }

    public List getByFK(String fk, Object value) {
        return getHibernateTemplate().find("from " + clazz.getName() + " o where o." + fk + " = " + value);
    }

    public List find(String query, Object[] params) {
        return getHibernateTemplate().find(query, params);
    }

    public List paginatedFind(final String query, final Object[] params, final int pageSize, final int page) {

        HibernateCallback callback = new HibernateCallback() {
                                    
            public Object doInHibernate(Session session) throws HibernateException {
                Query q = session.createQuery(query).setFirstResult((page * pageSize) - pageSize)
                        .setMaxResults(pageSize + 1);
                
                if(params != null) {
                    for (int i = 0; i < params.length; i++) {
                        Object object = params[i];

                        q.setParameter(i, object);
                    }
                }
                
                return q.list();
            }
        };
                        
        return (List) getHibernateTemplate().execute(callback);
    }
    
    public int getCount(final String query, final Object[] countParams){
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException {
                
                Query q = session.createQuery(query);
                
                if(countParams != null) {
                    for (int i = 0; i < countParams.length; i++) {
                        Object object = countParams[i];

                        q.setParameter(i, object);
                    }
                }
                
                return q.uniqueResult();
            }
        };
                
        return ((Long) getHibernateTemplate().execute(callback)).intValue();
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }
}
