package main.code.dto;

public class MenuDTO {

    private Integer Pk_Menu;
    private String Nom;
    private Integer Prix_Unitaire;

    // Constructeurs, getters et setters
    public MenuDTO() {}

    public MenuDTO(Integer Pk_Menu, String Nom, Integer Prix_Unitaire) {
        this.Pk_Menu = Pk_Menu;
        this.Nom = Nom;
        this.Prix_Unitaire = Prix_Unitaire;
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
        return Prix_Unitaire;
    }

    public void setPrixUnitaire(Integer Prix_Unitaire) {
        this.Prix_Unitaire = Prix_Unitaire;
    }

}
