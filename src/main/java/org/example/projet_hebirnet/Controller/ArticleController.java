package org.example.projet_hebirnet.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projet_hebirnet.Model.DaoArticle;
import org.example.projet_hebirnet.Model.Article;

import java.io.IOException;
import java.util.List;

@WebServlet("/app")
public class ArticleController extends HttpServlet {

    private DaoArticle articleDao;

    @Override
    public void init() throws ServletException {
        articleDao = DaoArticle.getInstance();
    }

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

    private void listArticles(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Article> articles = articleDao.findAll();
            req.setAttribute("articles", articles);
            req.getRequestDispatcher("/WEB-INF/listeArticles.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Erreur lors de la récupération des articles");
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Article.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String code = req.getParameter("code");
            Article article = articleDao.findByCode(code);
            req.setAttribute("article", article);
            req.getRequestDispatcher("/WEB-INF/EditArticle.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/app?action=list");
        }
    }

    private void createArticle(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            String code = req.getParameter("code");
            String designation = req.getParameter("designation");
            float prix = Float.parseFloat(req.getParameter("prix"));

            Article newArticle = new Article(code, designation, prix);
            articleDao.create(newArticle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/app?action=list");
    }

    private void updateArticle(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            String code = req.getParameter("code");
            String designation = req.getParameter("designation");
            float prix = Float.parseFloat(req.getParameter("prix"));

            Article updatedArticle = new Article(code, designation, prix);
            articleDao.update(updatedArticle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/app?action=list");
    }

    private void deleteArticle(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            String code = req.getParameter("code");
            articleDao.delete(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/app?action=list");
    }

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