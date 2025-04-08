package main.src.beans;

public class Entreprise {

    private Integer pkEntreprise;
    private String nom;
    private String adresse;

    public Entreprise() {
    }

    public Entreprise(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public Integer getPkEntreprise() {
        return pkEntreprise;
    }

    public void setPkEntreprise(Integer pkEntreprise) {
        this.pkEntreprise = pkEntreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

