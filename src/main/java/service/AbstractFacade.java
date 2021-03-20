/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 *
 * @author mac
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract Session getSession();

    public void createOrUpdate(T entity) {
        Transaction tx = null;
        Session session = getSession();

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();

        }
        catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }

    }


    public void remove(T entity) {
        Transaction tx = null;
        Session session = getSession();
        try {
            tx= session.beginTransaction();
            session.delete(session.merge(entity));
            tx.commit();

        }
        catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }

    }


    public T find(Long id) {

        return (T) getSession().get(entityClass, id);
    }

    public List<T> findAll() {
        return getSession().createCriteria(entityClass).list();
    }
}
