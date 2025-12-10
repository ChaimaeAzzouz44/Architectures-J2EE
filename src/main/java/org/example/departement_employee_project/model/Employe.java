package org.example.departement_employee_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TEmploye")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employe {
    @Id
    @Column(name ="IdEmp")
    private String idEmp;

    @Column(name = "NomEmp")
    private String nomEmp;

    @Column(name ="Salaire")
    private Float salaire;

    @ManyToOne
    @JoinColumn(name ="RefDept") //Exactement Identique au sql
    private Departement departement;

    public Employe() {
    }

    public Employe(String idEmp, String nomEmp, Float salaire) {
        this.idEmp = idEmp;
        this.nomEmp = nomEmp;
        this.salaire = salaire;
    }

    public Departement getDepartement() {
        return departement;
    }

    public String getIdEmp() {
        return idEmp;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public Float getSalaire() {
        return salaire;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public void setIdEmp(String idEmp) {
        this.idEmp = idEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
    }
}
