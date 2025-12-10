package org.example.departement_employee_project.service;

import org.example.departement_employee_project.model.Employe;

import java.util.List;

public interface EmployeService {
    public List<Employe> getAllEmployes();
    public Employe getEmployeById(String id);
    public void saveEmploye(Employe employee);
    public void deleteEmploye(String Empl_id);
    public void updateEmploye(String id, Employe employee);
}
