package client.servicerestclient.beans;

public class Menu {

    private int pkMenu;

    private String nom;

    private String image;

    private float prix;

    private int FK_Entreprise;

    private int quantite;

    public Menu() {
    }

    public Menu(String nom, String image, float prix, int FK_Entreprise, int quantite) {
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.FK_Entreprise = FK_Entreprise;
        this.quantite = quantite;
    }

    // Getters et Setters

    public int getPkMenu() {
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

    public int getFKEntreprise() {
        return FK_Entreprise;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setPKMenu(int pkMenu) {
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

    public void setFKEntreprise(int FK_Entreprise) {
        this.FK_Entreprise = FK_Entreprise;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
