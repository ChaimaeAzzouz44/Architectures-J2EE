package org.example.departement_employee_project.controller;

import org.example.departement_employee_project.model.Departement;
import org.example.departement_employee_project.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myapp/departement")
public class DepartementController {
    @Autowired
    private DepartementService departementService;

    @GetMapping
    public List<Departement> listerDepartement() {
        return departementService.getAllDepartements();
    }

    @GetMapping("/{id}")
    public Departement getDepartement(@PathVariable String id) {
        return departementService.getDepartementByID(id);
    }

    @PostMapping
    public void addDepartement(@RequestBody Departement departement) {
        departementService.saveDepartement(departement);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartement(@PathVariable String id) {
        departementService.DeleteDepartement(id);
    }
}
