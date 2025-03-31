package main.code.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_menu")
public class Menu {

    @Id
    @Column(name = "Pk_Menu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_menu;
    
    @Column(name = "Nom", length = 50)
    private String nom;

    @Column(name = "PrixUnitaire", length = 50)
    private Integer prixUnitaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fk_Entreprise")
    private Entreprise entreprise;

    // Getters et Setters
    public Integer getPkMenu() {
        return pk_menu;
    }

    public void setPkMenu(Integer pk_menu) {
        this.pk_menu = pk_menu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Integer prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

}
