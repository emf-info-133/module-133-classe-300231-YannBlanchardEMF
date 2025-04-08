package main.code.dto;

public class MenuDTO {

    private Integer pk_menu;
    private String nom;
    private String image;
    private float prix_unitaire;
    private Integer fkEntreprise;

    public MenuDTO() {}

    public MenuDTO(Integer pk_menu, String nom, String image, float prix_unitaire, Integer fkEntreprise) {
        this.pk_menu = pk_menu;
        this.nom = nom;
        this.image = image;
        this.prix_unitaire = prix_unitaire;
        this.fkEntreprise = fkEntreprise;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrixUnitaire() {
        return prix_unitaire;
    }

    public void setPrixUnitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public Integer getFkEntreprise() {
        return fkEntreprise;
    }

    public void setFkEntreprise(Integer fkEntreprise) {
        this.fkEntreprise = fkEntreprise;
    }
}
