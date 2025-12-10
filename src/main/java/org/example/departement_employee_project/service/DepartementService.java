package org.example.departement_employee_project.service;

import org.example.departement_employee_project.model.Departement;
import java.util.List;

public interface DepartementService {
    public List<Departement> getAllDepartements();
    public Departement getDepartementByID(String dprt_id);
    public void saveDepartement(Departement departement);
    public void DeleteDepartement(String dprt_id);
}
