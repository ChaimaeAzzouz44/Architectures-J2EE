package org.example.getsionecolefeliere.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.getsionecolefeliere.Model.Filiere;

import java.io.Serializable;
import java.util.List;

public class FiliereDAO implements ICRUD<Filiere> {

    @Override
    public void create(Filiere filiere) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(filiere);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Filiere filiere) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(filiere);
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
            Filiere filiere = em.find(Filiere.class, id);
            if (filiere != null) {
                em.remove(filiere);
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
    public Filiere findById(Serializable id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(Filiere.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Filiere> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Filiere f", Filiere.class).getResultList();
        } finally {
            em.close();
        }
    }
}