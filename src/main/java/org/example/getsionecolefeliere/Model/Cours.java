package org.example.getsionecolefeliere.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cours")
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "code", nullable = false, length = 50, unique = true)
    private String code;

    @Column(name = "intitule", nullable = false)
    private String intitule;

    @ManyToMany
    @JoinTable(
            name = "filiere_cours",
            joinColumns = @JoinColumn(name = "cours_id"),
            inverseJoinColumns = @JoinColumn(name = "filiere_id"))
    private List<Filiere> filieres; // Relation ManyToMany avec Filiere

    @ManyToMany
    @JoinTable(
            name = "eleve_cours",
            joinColumns = @JoinColumn(name = "cours_id"),
            inverseJoinColumns = @JoinColumn(name = "eleve_id"))
    private List<Eleve> eleves; // Relation ManyToMany avec Eleve

    public Cours(String code, Long id, String intitule) {
        this.code = code;
        this.id = id;
        this.intitule = intitule;
    }
    public Cours() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

}