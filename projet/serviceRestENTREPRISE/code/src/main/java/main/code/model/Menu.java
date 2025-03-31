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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pk_Menu")
    private Integer Pk_Menu;

    @Column(name = "Nom")
    private String Nom;

    @Column(name = "PrixUnitaire")
    private Integer PrixUnitaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fk_Entreprise")
    private Entreprise entreprise;

    // Getters et Setters
    public Integer getPkMenu() {
        return Pk_Menu;
    }

    public void setPkMenu(Integer Pk_Menu) {
        this.Pk_Menu = Pk_Menu;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public Integer getPrixUnitaire() {
        return PrixUnitaire;
    }

    public void setPrixUnitaire(Integer PrixUnitaire) {
        this.PrixUnitaire = PrixUnitaire;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

}
