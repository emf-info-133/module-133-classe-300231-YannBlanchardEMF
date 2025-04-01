package main.code.dto;

import main.code.model.Entreprise;

public class UserResponse {
    private Integer pk_user;
    private String username;
    private Integer fk_entreprise;

    public UserResponse(Integer pk_user, String username, Entreprise entreprise) {
    }

    public Integer getPk_user() {
        return pk_user;
    }

    public void setPk_user(Integer pk_user) {
        this.pk_user = pk_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFkEntreprise() {
        return fk_entreprise;
    }

    public void setFkEntreprise(Integer fk_entreprise) {
        this.fk_entreprise = fk_entreprise;
    }
}
