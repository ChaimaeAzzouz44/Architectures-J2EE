package org.example.webjava_sc1.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.webjava_sc1.Dao.ArticleDao;
import org.example.webjava_sc1.Model.Article;

import java.io.IOException;

@WebServlet("/app")
public class ArticleController extends HttpServlet {
    private ArticleDao articleDao;

    @Override
    public void init() throws ServletException {
        articleDao = new ArticleDao();
        articleDao.Initiate();
        getServletContext().setAttribute("articleDao", articleDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action != null ? action : "") {
            case "list":
                // Afficher la liste des articles
                req.setAttribute("articles", articleDao.getAlL());
                req.getRequestDispatcher("/WEB-INF/view/listeArticles.jsp").forward(req, resp);
                break;
            case "edit":
                // Afficher le formulaire pour éditer un article
                String codeToEdit = req.getParameter("code");
                articleDao.findByCode(codeToEdit).ifPresent(article -> req.setAttribute("article", article));
                req.getRequestDispatcher("/WEB-INF/view/EditArticle.jsp").forward(req, resp);
                break;
            case "new":
                req.getRequestDispatcher("/WEB-INF/view/Article.jsp").forward(req, resp);
                break;
            default:
                // Par défaut, rediriger vers la liste
                resp.sendRedirect(req.getContextPath() + "/app?action=list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action != null ? action : "") {
            case "create":
                // Créer un nouvel article
                String createCode = req.getParameter("code");
                String createDesignation = req.getParameter("designation");
                double createPrix = 0;
                try {
                    createPrix = Double.parseDouble(req.getParameter("prix"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    resp.sendRedirect(req.getContextPath() + "/app?action=new");
                    return;
                }
                articleDao.Create(new Article(createCode, createDesignation, createPrix));
                resp.sendRedirect(req.getContextPath() + "/app?action=list");
                break;

            case "update":
                // Mettre à jour un article existant
                String updateCode = req.getParameter("code");
                String updateDesignation = req.getParameter("designation");
                double updatePrix = Double.parseDouble(req.getParameter("prix"));

                // Mettre à jour l'article
                articleDao.update(updateCode, updateDesignation, updatePrix);
                resp.sendRedirect(req.getContextPath() + "/app?action=list");
                break;

            case "delete":
                // Supprimer un article
                String deleteCode = req.getParameter("code");
                articleDao.findByCode(deleteCode).ifPresent(articleDao::Delete);
                resp.sendRedirect(req.getContextPath() + "/app?action=list");
                break;

            default:
                // Rediriger vers la liste des articles si aucune action correspondante
                resp.sendRedirect(req.getContextPath() + "/app?action=list");
                break;
        }
    }







}
