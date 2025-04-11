package main.code.model;




public class Entreprise {

    private int pk_entreprise;
    
    private String nom;

    // Getters et Setters
    public int getPkEntreprise() {
        return pk_entreprise;
    }

    public void setPkEntreprise(int pk_entreprise) {
        this.pk_entreprise = pk_entreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
