package main.code.dto;

import main.code.model.Entreprise;

public class UserResponse {
    private int pk_user;
    private String username;
    private int fk_entreprise;

    public UserResponse(int pk_user, String username, int entreprise) {
    }

    public int getPk_user() {
        return pk_user;
    }

    public void setPk_user(int pk_user) {
        this.pk_user = pk_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFkEntreprise() {
        return fk_entreprise;
    }

    public void setFkEntreprise(int fk_entreprise) {
        this.fk_entreprise = fk_entreprise;
    }
}
