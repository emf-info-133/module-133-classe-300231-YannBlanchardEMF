package main.src.dto;

public class EntrepriseDTO {
    private Integer pk_menu;
    private String nom;
    private Integer prix_unitaire;

    // Constructeurs, getters et setters
    public EntrepriseDTO() {}

    public EntrepriseDTO(Integer pk_menu, String nom, Integer prix_unitaire) {
        this.pk_menu = pk_menu;
        this.nom = nom;
        this.prix_unitaire = prix_unitaire;
    }

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
        return prix_unitaire;
    }

    public void setPrixUnitaire(Integer prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }
}

