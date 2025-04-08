package main.code.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_Entreprise")
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pk_Entreprise")
    private Integer pk_entreprise;
    
    @Column(name = "Nom", length = 50)
    private String nom;

    // Getters et Setters
    public Integer getPkEntreprise() {
        return pk_entreprise;
    }

    public void setPkEntreprise(Integer pk_entreprise) {
        this.pk_entreprise = pk_entreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
