package main.src.beans;

public class Menu {

    private int pkMenu;
    private String nom;
    private String image;
    private float prix;
    private Integer fkEntreprise;
    private int quantite; // ✅ le champ manquant

    public Menu() {}

    public Menu(Integer pk_menu, String nom, String image, float prix, Integer fkEntreprise) {
        this.pkMenu = pk_menu;
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.fkEntreprise = fkEntreprise;
    }

    // Getters & Setters

    public Integer getPkMenu() {
        return pkMenu;
    }

    public void setPkMenu(Integer pkMenu) {
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

    public Integer getFkEntreprise() {
        return fkEntreprise;
    }

    public void setFkEntreprise(Integer fkEntreprise) {
        this.fkEntreprise = fkEntreprise;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
