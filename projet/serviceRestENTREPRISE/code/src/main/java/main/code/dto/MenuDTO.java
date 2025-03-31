package main.code.dto;

public class MenuDTO {

    private Integer pk_menu;
    private String nom;
    private Integer prixUnitaire;

    // Constructeurs, getters et setters
    public MenuDTO() {}

    public MenuDTO(Integer pk_menu, String nom, Integer prixUnitaire) {
        this.pk_menu = pk_menu;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
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
        return prixUnitaire;
    }

    public void setPrixUnitaire(Integer prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

}
