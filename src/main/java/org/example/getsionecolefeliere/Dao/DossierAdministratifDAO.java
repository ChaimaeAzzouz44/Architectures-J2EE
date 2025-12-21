package org.example.getsionecolefeliere.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.getsionecolefeliere.Model.DossierAdministratif;

import java.io.Serializable;
import java.util.List;

public class DossierAdministratifDAO implements ICRUD<DossierAdministratif> {

    @Override
    public void create(DossierAdministratif dossier) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(dossier);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(DossierAdministratif dossier) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(dossier);
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
            DossierAdministratif dossier = em.find(DossierAdministratif.class, id);
            if (dossier != null) {
                em.remove(dossier);
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
    public DossierAdministratif findById(Serializable id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(DossierAdministratif.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<DossierAdministratif> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            // ✅ FETCH JOIN pour charger l'élève immédiatement
            return em.createQuery(
                            "SELECT d FROM DossierAdministratif d JOIN FETCH d.eleve",
                            DossierAdministratif.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Méthode supplémentaire : Trouver un dossier par élève
    public DossierAdministratif findByEleve(Long eleveId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT d FROM DossierAdministratif d WHERE d.eleve.id = :eleveId",
                            DossierAdministratif.class)
                    .setParameter("eleveId", eleveId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    // Méthode supplémentaire : Trouver par numéro d'inscription
    public DossierAdministratif findByNumeroInscription(String numero) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT d FROM DossierAdministratif d WHERE d.numeroInscription = :numero",
                            DossierAdministratif.class)
                    .setParameter("numero", numero)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}