package main.src.dto;

import java.util.ArrayList;
import main.src.beans.Menu;

public class ClientDTO {

    // Pour login
    private String login;
    private String password;
    private Integer fkEntreprise;

    // Pour register
    private String nom;
    private String prenom;
    private boolean admin;


    // Pour commande
    private float total;
    private ArrayList<Menu> menus;

    // Pour getUserById
    private Integer id;

    public ClientDTO() {}

    // Getters & Setters

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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
