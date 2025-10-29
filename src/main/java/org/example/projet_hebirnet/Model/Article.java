package org.example.projet_hebirnet.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "designation", nullable = false, length = 100)
    private String designation;

    @Column(name = "prix", nullable = false)
    private Float prix;

    public Article() {
    }

    public Article(String code, String designation, Float prix) {
        this.code = code;
        this.designation = designation;
        this.prix = prix;
    }

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

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }
}