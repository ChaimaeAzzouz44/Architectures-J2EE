package org.example.projet_hebirnet.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projet_hebirnet.Model.DaoArticle;
import org.example.projet_hebirnet.Model.HibernateUtil;
import org.example.projet_hebirnet.Model.Article;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

@WebServlet("/app")
public class ArticleController extends HttpServlet {

    private DaoArticle articleDao;

    @Override
    public void init() throws ServletException {
        articleDao = DaoArticle.getInstance(); // Singleton
    }

    //methode central
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listArticles(req, resp);
                break;
            case "new":
                showNewForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "create":
                createArticle(req, resp);
                break;
            case "update":
                updateArticle(req, resp);
                break;
            case "delete":
                deleteArticle(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/app?action=list");
                break;
        }
    }

    //implementation des methodes

    private void listArticles(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Article> articles = articleDao.findAll();
            tx.commit();

            req.setAttribute("articles", articles);
            req.getRequestDispatcher("/WEB-INF/listeArticles.jsp").forward(req, resp);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Article.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String code = req.getParameter("code");
        Article article = articleDao.findByCode(code);
        req.setAttribute("article", article);
        req.getRequestDispatcher("/WEB-INF/EditArticle.jsp").forward(req, resp);
    }

    private void createArticle(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String code = req.getParameter("code");
        String designation = req.getParameter("designation");
        double prix = Double.parseDouble(req.getParameter("prix"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Article newArticle = new Article(code, designation, (float) prix);
            articleDao.create(newArticle);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        resp.sendRedirect(req.getContextPath() + "/app?action=list");
    }

    private void updateArticle(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String code = req.getParameter("code");
        String designation = req.getParameter("designation");
        double prix = Double.parseDouble(req.getParameter("prix"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Article updatedArticle = new Article(code, designation, (float) prix);
            articleDao.update(updatedArticle);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        resp.sendRedirect(req.getContextPath() + "/app?action=list");
    }

    private void deleteArticle(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String code = req.getParameter("code");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            articleDao.delete(code);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        resp.sendRedirect(req.getContextPath() + "/app?action=list");
    }

    // rederiction des methodes http

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }
}
