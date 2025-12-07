package org.example.tuto_springwebspringjdbc.model;


public class Article{

    public String code;
    public String designation;
    public double prix;
    public Article() {
    }
    public Article(String Code, String Name, double Prix) {
        this.code = Code;
        this.designation = Name;
        this.prix = Prix;
    }
    public Article(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getDesignation() {
        return designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Article " + code + " - " + designation + " - " + prix+"\n";
    }
}