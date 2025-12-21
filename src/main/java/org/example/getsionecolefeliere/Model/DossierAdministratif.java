package org.example.getsionecolefeliere.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "dossier_administratif")
public class DossierAdministratif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @Column(name = "numero_inscription", nullable = false)
    private String numeroInscription;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "eleve_id", nullable = false)
    private Eleve eleve;

    public DossierAdministratif(LocalDate dateCreation, Eleve eleve, Long id, String numeroInscription) {
        this.dateCreation = dateCreation;
        this.eleve = eleve;
        this.id = id;
        this.numeroInscription = numeroInscription;
    }

    public DossierAdministratif() {
    }

    public String getNumeroInscription() { return numeroInscription; }

    public void setNumeroInscription(String numeroInscription) {
        this.numeroInscription = numeroInscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

}