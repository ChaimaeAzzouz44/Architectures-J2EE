package org.example.departement_employee_project.service;

import org.example.departement_employee_project.model.Departement;
import org.example.departement_employee_project.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementServiceImp implements DepartementService {
    @Autowired
    private DepartementRepository departementRepository;
    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement getDepartementByID(String dprt_id) {
        return departementRepository.findById(dprt_id).orElse(null);
    }

    @Override
    public void saveDepartement(Departement departement) {
        departementRepository.save(departement);
    }

    @Override
    public void DeleteDepartement(String dprt_id) {
        departementRepository.deleteById(dprt_id);
    }
}
