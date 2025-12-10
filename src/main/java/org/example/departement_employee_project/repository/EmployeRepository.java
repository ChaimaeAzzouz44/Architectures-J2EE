package org.example.departement_employee_project.repository;

import org.example.departement_employee_project.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, String> {
    public List<Employe> getEmployesByDepartement(String departement);
}
