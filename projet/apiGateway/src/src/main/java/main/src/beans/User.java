package main.src.beans;

public class User {
    private int pk;
    private String nom;
    private String prenom;
    private String login;
    private boolean admin;
    private Integer fkEntreprise;

    public User() {}

    // Getters et Setters
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Integer getFKEntreprise() {
        return fkEntreprise;
    }

    public void setFKEntreprise(Integer fkEntreprise) {
        this.fkEntreprise = fkEntreprise;
    }
}
