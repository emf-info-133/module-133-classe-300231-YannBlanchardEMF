package main.src.beans;

public class Menu {

    private int pkMenu;
    private String nom;
    private String image;
    private float prix;
    private int fkEntreprise;
    private int quantite;

    public Menu() {}

    public Menu(String nom, String image, float prix, int fkEntreprise, int quantite) {
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.fkEntreprise = fkEntreprise;
        this.quantite = quantite;
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
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
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
