package org.example.projet_hebirnet.Model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class DaoArticle implements IDao<Article> {
    private static DaoArticle instance = new DaoArticle();

    private DaoArticle() {
    }

    public static DaoArticle getInstance() {
        return instance;
    }

    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            articles = session.createQuery("FROM Article", Article.class).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return articles;
    }

    public Article findByCode(String code) {
        Session session = null;
        Article article = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            article = session.get(Article.class, code);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return article;
    }

    public boolean create(Article article) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Article existing = session.get(Article.class, article.getCode());
            if (existing != null) {
                return false;
            }

            session.save(article);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    public boolean update(Article article) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(article);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    public boolean delete(String code) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Article article = session.get(Article.class, code);
            if (article != null) {
                session.delete(article);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }
}
