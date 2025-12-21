package org.example.getsionecolefeliere.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.getsionecolefeliere.Model.Eleve;

import java.io.Serializable;
import java.util.List;

public class EleveDAO implements ICRUD<Eleve> {

    @Override
    public void create(Eleve eleve) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(eleve);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Eleve eleve) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(eleve);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Serializable id) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Eleve eleve = em.find(Eleve.class, id);
            if (eleve != null) {
                em.remove(eleve);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Eleve findById(Serializable id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(Eleve.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Eleve> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Eleve e", Eleve.class).getResultList();
        } finally {
            em.close();
        }
    }
}