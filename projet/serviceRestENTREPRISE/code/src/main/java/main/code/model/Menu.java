package main.code.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Menu")
    private Integer pkMenu;

    @Column(name = "nom")
    private String nom;

    @Column(name = "image")
    private String image;

    @Column(name = "prix")
    private float prix;

    @Column(name = "FK_Entreprise")
    private Integer fkEntreprise;


    public Menu() {}

    public Menu(String nom, String image, float prix, Integer fkEntreprise) {
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.fkEntreprise = fkEntreprise;
    }

    // Getters et Setters

    public Integer getPkMenu() {
        return pkMenu;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public float getPrix() {
        return prix;
    }

    public Integer getFkEntreprise() {
        return fkEntreprise;
    }

    public void setPKMenu(Integer pkMenu) {
        this.pkMenu = pkMenu;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setFkEntreprise(Integer fkEntreprise) {
        this.fkEntreprise = fkEntreprise;
    }
}

