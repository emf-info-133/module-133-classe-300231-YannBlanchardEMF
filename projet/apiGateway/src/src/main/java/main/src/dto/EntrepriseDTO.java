package main.src.dto;

public class EntrepriseDTO {
    private int pkMenu;
    private String nom;
    private String image;
    private float prix_unitaire;
    private int quantite;
    private int fkEntreprise;

    public EntrepriseDTO() {}

    public EntrepriseDTO(int pk_menu, String nom, String image, float prix_unitaire, int quantite, int fkEntreprise) {
        this.nom = nom;
        this.image = image;
        this.prix_unitaire = prix_unitaire;
        this.quantite = quantite;
        this.fkEntreprise = fkEntreprise;
    }

    // Getters & Setters

    public int getPkMenu() {
        return pkMenu;
    }

    public void setPkMenu(int pkMenu) {
        this.pkMenu = pkMenu;
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

    public float getPrix() {
        return prix_unitaire;
    }

    public void setPrix(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public int getFkEntreprise() {
        return fkEntreprise;
    }

    public void setFkEntreprise(int fkEntreprise) {
        this.fkEntreprise = fkEntreprise;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}

