package main.code.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_entreprise")
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Enterprise")
    private Integer pkEntreprise;
    
    @Column(name = "Nom", length = 50)
    private String nom;

    // Getters et Setters
    public Integer getId() {
        return pkEntreprise;
    }

    public void setId(Integer pkEntreprise) {
        this.pkEntreprise = pkEntreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
