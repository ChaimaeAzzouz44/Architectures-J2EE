package org.example.projet_hebirnet.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "designation", nullable = false, length = 100)
    private String designation;

    @Column(name = "prix", nullable = false, precision = 10, scale = 2)
    private Float prix;


    public Article() {
    }

    public Article(String code, String designation, float prix) {
        this.prix = prix;
        this.designation = designation;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String id) {
        this.code = id;
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