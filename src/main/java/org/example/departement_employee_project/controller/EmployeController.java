package org.example.departement_employee_project.controller;

import org.example.departement_employee_project.model.Employe;
import org.example.departement_employee_project.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myapp/employe")
public class EmployeController {
    @Autowired
    private EmployeService employeService;

    @GetMapping
    public List<Employe> ListerEmploye() {
        return employeService.getAllEmployes();
    }

    @GetMapping("/{id}")
    public Employe GetEmploye(@PathVariable String id) {
        return employeService.getEmployeById(id);
    }

    @PostMapping
    public void AddEmploye(@RequestBody Employe employee) {
        employeService.saveEmploye(employee);
    }

    @DeleteMapping("/{id}")
    public void DeleteEmploye(@PathVariable String id) {
        employeService.deleteEmploye(id);
    }

    @PutMapping("/{id}")
    public void UpdateEmploye(@PathVariable String id, @RequestBody Employe employee) {
        employeService.updateEmploye(id, employee);
    }
    //@RequestBody permet de récupérer des données JSON envoyées dans le
    // corps (body) de la requête HTTP
    // et de les convertir automatiquement en objet Java.


    //@PathVariable permet de récupérer une valeur directement depuis l'URL.
}
