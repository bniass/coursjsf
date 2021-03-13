/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

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

    public void create(T entity) {
        Transaction tx = null;
        Session session = getSession();

        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();

        }
        catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }

    }

    public void edit(T entity) {
        Transaction tx = null;
        Session session = getSession();

        try {
            tx = session.beginTransaction();
            session.merge(entity);
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
    /*

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }*/


}
