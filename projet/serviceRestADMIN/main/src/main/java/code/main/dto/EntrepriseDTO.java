package code.main.dto;

public class EntrepriseDTO {
    private Integer id;
    private String nom;
    private String adresse;

    // Constructeur vide
    public EntrepriseDTO() {}

    // Constructeur avec paramètres
    public EntrepriseDTO(Integer id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

