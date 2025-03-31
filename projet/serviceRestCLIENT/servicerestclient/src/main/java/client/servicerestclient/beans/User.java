package client.servicerestclient.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "T_Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Users")
    private int id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "mdp", nullable = false)
    private String password;

    @Column(name = "isAdmin", nullable = false)
    private boolean isAdmin;

    @Column(name = "FK_Entreprise")
    private Integer FK_Entreprise;

    public User() {
    }

    public User(String nom, String prenom, String login, String password, boolean isAdmin, Integer FK_Entreprise) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.FK_Entreprise = FK_Entreprise;
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getIdEntreprise() {
        return FK_Entreprise;
    }

    public void setIdEntreprise(Integer FK_Entreprise) {
        this.FK_Entreprise = FK_Entreprise;
    }
}
