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
    @Column(name = "Pk_Entreprise")
    private Integer Pk_Entreprise;

    @Column(name = "Nom")
    private String Nom;

    // Getters et Setters
    public Integer getPkEntreprise() {
        return Pk_Entreprise;
    }

    public void setPKEntreprise(Integer Pk_Entreprise) {
        this.Pk_Entreprise = Pk_Entreprise;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }
}
