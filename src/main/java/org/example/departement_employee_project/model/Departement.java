package org.example.departement_employee_project.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.List;

@Entity
@Table(name = "TDepartement")
public class Departement {
    @Id
    @Column(name = "iddept")
    private String iddept;

    @Column(name = "nomdept")
    private String nomdept;

    @OneToMany(mappedBy = "departement")
    @JsonIgnore  //empeche la boucle infinie JSON
    //Employe -> Departement -> Employes -> Employe -> Departement -> ...

    private List<Employe> employes;

    public Departement() {
    }

    public Departement(String iddept, String nomdept) {
        this.iddept = iddept;
        this.nomdept = nomdept;
    }

    public Departement(List<Employe> employes, String iddept, String nomdept) {
        this.employes = employes;
        this.iddept = iddept;
        this.nomdept = nomdept;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public String getIddept() {
        return iddept;
    }

    public String getNomdept() {
        return nomdept;
    }

    public void setIddept(String iddept) {
        this.iddept = iddept;
    }

    public void setNomdept(String nomdept) {
        this.nomdept = nomdept;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
}
