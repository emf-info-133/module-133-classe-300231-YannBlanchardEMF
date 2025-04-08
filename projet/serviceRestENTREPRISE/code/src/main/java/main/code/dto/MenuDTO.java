package main.code.dto;

public class MenuDTO {

    private int pk_menu;
    private String nom;
    private String image;
    private float prix_unitaire;
    private int fk_entreprise;

    public MenuDTO() {}

    public MenuDTO(int pk_menu, String nom, String image, float prix_unitaire, int fk_entreprise) {
        this.pk_menu = pk_menu;
        this.nom = nom;
        this.image = image;
        this.prix_unitaire = prix_unitaire;
        this.fk_entreprise = fk_entreprise;
    }

    public int getPkMenu() {
        return pk_menu;
    }

    public void setPkMenu(int pk_menu) {
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

    public int getFkEntreprise() {
        return fk_entreprise;
    }

    public void setFkEntreprise(int fk_entreprise) {
        this.fk_entreprise = fk_entreprise;
    }
}
