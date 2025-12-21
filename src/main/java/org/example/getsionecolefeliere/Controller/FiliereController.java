package org.example.getsionecolefeliere.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.getsionecolefeliere.Dao.FiliereDAO;
import org.example.getsionecolefeliere.Model.Filiere;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FiliereController", urlPatterns = {"/filieres"})
public class FiliereController extends HttpServlet {

    private FiliereDAO filiereDAO;

    @Override
    public void init() throws ServletException {
        filiereDAO = new FiliereDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listFilieres(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "showEditForm":
                showEditForm(request, response);
                break;
            case "delete":
                deleteFiliere(request, response);
                break;
            default:
                listFilieres(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertFiliere(request, response);
        } else if ("update".equals(action)) {
            updateFiliere(request, response);
        }
    }

    // Liste toutes les filières
    private void listFilieres(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Filiere> filieres = filiereDAO.findAll();
        request.setAttribute("filieres", filieres);
        request.getRequestDispatcher("/WEB-INF/views/filiere/list.jsp").forward(request, response);
    }

    // Afficher le formulaire d'ajout
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/filiere/form.jsp").forward(request, response);
    }

    // Afficher le formulaire de modification
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Filiere filiere = filiereDAO.findById(id);
        request.setAttribute("filiere", filiere);
        request.getRequestDispatcher("/WEB-INF/views/filiere/form.jsp").forward(request, response);
    }

    // Insérer une nouvelle filière
    private void insertFiliere(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String code = request.getParameter("code");
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");

        Filiere filiere = new Filiere();
        filiere.setCode(code);
        filiere.setNom(nom);
        filiere.setDescription(description);

        filiereDAO.create(filiere);
        response.sendRedirect(request.getContextPath() + "/filieres?action=list");
    }

    // Mettre à jour une filière
    private void updateFiliere(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String code = request.getParameter("code");
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");

        Filiere filiere = filiereDAO.findById(id);
        filiere.setCode(code);
        filiere.setNom(nom);
        filiere.setDescription(description);

        filiereDAO.update(filiere);
        response.sendRedirect(request.getContextPath() + "/filieres?action=list");
    }

    // Supprimer une filière
    private void deleteFiliere(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        filiereDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/filieres?action=list");
    }
}