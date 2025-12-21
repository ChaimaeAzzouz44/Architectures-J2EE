package org.example.getsionecolefeliere.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.getsionecolefeliere.Dao.DossierAdministratifDAO;
import org.example.getsionecolefeliere.Dao.EleveDAO;
import org.example.getsionecolefeliere.Model.DossierAdministratif;
import org.example.getsionecolefeliere.Model.Eleve;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "DossierAdministratifController", urlPatterns = {"/dossiers"})
public class DossierAdministratifController extends HttpServlet {

    private DossierAdministratifDAO dossierDAO;
    private EleveDAO eleveDAO;

    @Override
    public void init() throws ServletException {
        dossierDAO = new DossierAdministratifDAO();
        eleveDAO = new EleveDAO();
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
                listDossiers(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "showEditForm":
                showEditForm(request, response);
                break;
            case "delete":
                deleteDossier(request, response);
                break;
            default:
                listDossiers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertDossier(request, response);
        } else if ("update".equals(action)) {
            updateDossier(request, response);
        }
    }

    private void listDossiers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<DossierAdministratif> dossiers = dossierDAO.findAll();
        request.setAttribute("dossiers", dossiers);
        request.getRequestDispatcher("/WEB-INF/views/dossier/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Eleve> eleves = eleveDAO.findAll();
        request.setAttribute("eleves", eleves);
        request.getRequestDispatcher("/WEB-INF/views/dossier/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        DossierAdministratif dossier = dossierDAO.findById(id);
        List<Eleve> eleves = eleveDAO.findAll();

        request.setAttribute("dossier", dossier);
        request.setAttribute("eleves", eleves);
        request.getRequestDispatcher("/WEB-INF/views/dossier/form.jsp").forward(request, response);
    }

    private void insertDossier(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String numeroInscription = request.getParameter("numeroInscription");
        String dateCreationStr = request.getParameter("dateCreation");
        Long eleveId = Long.parseLong(request.getParameter("eleveId"));

        Eleve eleve = eleveDAO.findById(eleveId);
        LocalDate dateCreation = LocalDate.parse(dateCreationStr);

        DossierAdministratif dossier = new DossierAdministratif();
        dossier.setNumeroInscription(numeroInscription);
        dossier.setDateCreation(dateCreation);
        dossier.setEleve(eleve);

        dossierDAO.create(dossier);
        response.sendRedirect(request.getContextPath() + "/dossiers?action=list");
    }

    private void updateDossier(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String numeroInscription = request.getParameter("numeroInscription");
        String dateCreationStr = request.getParameter("dateCreation");
        Long eleveId = Long.parseLong(request.getParameter("eleveId"));

        DossierAdministratif dossier = dossierDAO.findById(id);
        Eleve eleve = eleveDAO.findById(eleveId);
        LocalDate dateCreation = LocalDate.parse(dateCreationStr);

        dossier.setNumeroInscription(numeroInscription);
        dossier.setDateCreation(dateCreation);
        dossier.setEleve(eleve);

        dossierDAO.update(dossier);
        response.sendRedirect(request.getContextPath() + "/dossiers?action=list");
    }

    private void deleteDossier(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        dossierDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/dossiers?action=list");
    }
}