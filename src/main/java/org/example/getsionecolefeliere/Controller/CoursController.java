package org.example.getsionecolefeliere.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.getsionecolefeliere.Dao.CoursDAO;
import org.example.getsionecolefeliere.Model.Cours;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CoursController", urlPatterns = {"/cours"})
public class CoursController extends HttpServlet {

    private CoursDAO coursDAO;

    @Override
    public void init() throws ServletException {
        coursDAO = new CoursDAO();
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
                listCours(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "showEditForm":
                showEditForm(request, response);
                break;
            case "delete":
                deleteCours(request, response);
                break;
            default:
                listCours(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertCours(request, response);
        } else if ("update".equals(action)) {
            updateCours(request, response);
        }
    }

    private void listCours(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cours> coursList = coursDAO.findAll();
        request.setAttribute("coursList", coursList);
        request.getRequestDispatcher("/WEB-INF/views/cours/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/cours/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cours cours = coursDAO.findById(id);
        request.setAttribute("cours", cours);
        request.getRequestDispatcher("/WEB-INF/views/cours/form.jsp").forward(request, response);
    }

    private void insertCours(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String code = request.getParameter("code");
        String intitule = request.getParameter("intitule");

        Cours cours = new Cours();
        cours.setCode(code);
        cours.setIntitule(intitule);

        coursDAO.create(cours);
        response.sendRedirect(request.getContextPath() + "/cours?action=list");
    }

    private void updateCours(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String code = request.getParameter("code");
        String intitule = request.getParameter("intitule");

        Cours cours = coursDAO.findById(id);
        cours.setCode(code);
        cours.setIntitule(intitule);

        coursDAO.update(cours);
        response.sendRedirect(request.getContextPath() + "/cours?action=list");
    }

    private void deleteCours(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        coursDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/cours?action=list");
    }
}