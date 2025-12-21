package org.example.getsionecolefeliere.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.getsionecolefeliere.Model.Cours;

import java.io.Serializable;
import java.util.List;

public class CoursDAO implements ICRUD<Cours> {

    @Override
    public void create(Cours cours) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(cours);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Cours cours) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(cours);
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
            Cours cours = em.find(Cours.class, id);
            if (cours != null) {
                em.remove(cours);
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
    public Cours findById(Serializable id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(Cours.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cours> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cours c", Cours.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Méthode supplémentaire : Trouver un cours par code
    public Cours findByCode(String code) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cours c WHERE c.code = :code", Cours.class)
                    .setParameter("code", code)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}