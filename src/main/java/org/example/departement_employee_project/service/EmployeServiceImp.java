package org.example.departement_employee_project.service;

import org.example.departement_employee_project.model.Employe;
import org.example.departement_employee_project.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImp implements EmployeService {

    @Autowired
    private EmployeRepository employeRepository;
    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe getEmployeById(String id) {
        return employeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmploye(Employe employee) {
        employeRepository.save(employee);
    }

    @Override
    public void deleteEmploye(String Empl_id) {
        employeRepository.deleteById(Empl_id);
    }

    @Override
    public void updateEmploye(String id, Employe employee) {
        Employe existingEmployee = employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec l'id: " + id));

        existingEmployee.setNomEmp(employee.getNomEmp());
        existingEmployee.setSalaire(employee.getSalaire());
        existingEmployee.setDepartement(employee.getDepartement());

        employeRepository.save(existingEmployee);
    }
}
