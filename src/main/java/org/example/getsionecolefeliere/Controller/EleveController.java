package org.example.getsionecolefeliere.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.getsionecolefeliere.Dao.EleveDAO;
import org.example.getsionecolefeliere.Dao.FiliereDAO;
import org.example.getsionecolefeliere.Model.Eleve;
import org.example.getsionecolefeliere.Model.Filiere;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EleveController", urlPatterns = {"/eleves"})
public class EleveController extends HttpServlet {

    private EleveDAO eleveDAO;
    private FiliereDAO filiereDAO;

    @Override
    public void init() throws ServletException {
        eleveDAO = new EleveDAO();
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
                listEleves(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "showEditForm":
                showEditForm(request, response);
                break;
            case "delete":
                deleteEleve(request, response);
                break;
            default:
                listEleves(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertEleve(request, response);
        } else if ("update".equals(action)) {
            updateEleve(request, response);
        }
    }

    private void listEleves(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Eleve> eleves = eleveDAO.findAll();
        request.setAttribute("eleves", eleves);
        request.getRequestDispatcher("/WEB-INF/views/eleve/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Filiere> filieres = filiereDAO.findAll();
        request.setAttribute("filieres", filieres);
        request.getRequestDispatcher("/WEB-INF/views/eleve/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Eleve eleve = eleveDAO.findById(id);
        List<Filiere> filieres = filiereDAO.findAll();

        request.setAttribute("eleve", eleve);
        request.setAttribute("filieres", filieres);
        request.getRequestDispatcher("/WEB-INF/views/eleve/form.jsp").forward(request, response);
    }

    private void insertEleve(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String matricule = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        Long filiereId = Long.parseLong(request.getParameter("filiereId"));

        Filiere filiere = filiereDAO.findById(filiereId);

        Eleve eleve = new Eleve();
        eleve.setMatricula(matricule);
        eleve.setNom(nom);
        eleve.setPrenom(prenom);
        eleve.setEmail(email);
        eleve.setFiliere(filiere);

        eleveDAO.create(eleve);
        response.sendRedirect(request.getContextPath() + "/eleves?action=list");
    }

    private void updateEleve(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String matricule = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        Long filiereId = Long.parseLong(request.getParameter("filiereId"));

        Eleve eleve = eleveDAO.findById(id);
        Filiere filiere = filiereDAO.findById(filiereId);

        eleve.setMatricula(matricule);
        eleve.setNom(nom);
        eleve.setPrenom(prenom);
        eleve.setEmail(email);
        eleve.setFiliere(filiere);

        eleveDAO.update(eleve);
        response.sendRedirect(request.getContextPath() + "/eleves?action=list");
    }

    private void deleteEleve(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        eleveDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/eleves?action=list");
    }
}