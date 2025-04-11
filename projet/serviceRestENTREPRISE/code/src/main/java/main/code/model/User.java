package main.code.model;

public class User {

    private int pkUser;

    private String nom;

    private String prenom;

    private String login;

    private String password;

    private boolean isAdmin;

    private int fkEntreprise;

    public User() {
    }

    public User(String nom, String prenom, String login, String password, boolean isAdmin, int fkEntreprise) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.fkEntreprise = fkEntreprise;
    }

    // Getters & Setters

    public int getPK() {
        return pkUser;
    }

    public void setPK(int pkUser) {
        this.pkUser = pkUser;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean setAdmin(boolean statu) {
        return this.isAdmin = statu;
    }

    public Integer getFKEntreprise() {
        return fkEntreprise;
    }

    public void setFKEntreprise(Integer fkEntreprise) {
        this.fkEntreprise = fkEntreprise;
    }
}
