package main.code.dto;

public class MenuDTO {

    private Integer Pk_Menu;
    private String Nom;
    private Integer PrixUnitaire;

    // Constructeurs, getters et setters
    public MenuDTO() {}

    public MenuDTO(Integer Pk_Menu, String Nom, Integer PrixUnitaire) {
        this.Pk_Menu = Pk_Menu;
        this.Nom = Nom;
        this.PrixUnitaire = PrixUnitaire;
    }

    public Integer getPkMenu() {
        return Pk_Menu;
    }

    public void setPkMenu(Integer Pk_Menu) {
        this.Pk_Menu = Pk_Menu;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public Integer getPrixUnitaire() {
        return PrixUnitaire;
    }

    public void setPrixUnitaire(Integer PrixUnitaire) {
        this.PrixUnitaire = PrixUnitaire;
    }

}
