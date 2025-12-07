package org.example.springwebspringdata.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "article")
public class Article {

    @Id
    private String code;
    private String designation;
    private double prix;

    public Article(String code, String designation, double prix) {
        this.code = code;
        this.designation = designation;
        this.prix = prix;
    }
    public Article(){}
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }

}