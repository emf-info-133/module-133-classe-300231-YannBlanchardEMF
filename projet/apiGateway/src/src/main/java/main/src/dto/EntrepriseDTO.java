package main.src.dto;

public class EntrepriseDTO {
    private int pkMenu;
    private String nom;
    private String image;
    private Integer prix_unitaire;
    private Integer quantite;
    private Integer fkEntreprise;

    public EntrepriseDTO() {}

    public EntrepriseDTO(int pk_menu, String nom, String image, Integer prix_unitaire, Integer quantite, int fkEntreprise) {
        this.nom = nom;
        this.image = image;
        this.prix_unitaire = prix_unitaire;
        this.quantite = quantite;
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

    public Integer getPrix() {
        return prix_unitaire;
    }

    public void setPrix(Integer prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public Integer getFkEntreprise() {
        return fkEntreprise;
    }

    public void setFkEntreprise(Integer fkEntreprise) {
        this.fkEntreprise = fkEntreprise;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}

