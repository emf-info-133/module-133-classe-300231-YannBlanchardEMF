package main.src.dto;

public class AdminDTO {

    // Champs de UserDTO
    private String nom;
    private String prenom;
    private boolean admin;
    private String password;
    private Integer idEntreprise;

    // Champs de EntrepriseDTO
    private Integer entrepriseId;
    private String entrepriseNom;
    private String entrepriseAdresse;

    public AdminDTO() {
    }

    public AdminDTO(String nom, String prenom, boolean admin, String password,
                    Integer idEntreprise, Integer entrepriseId, String entrepriseNom, String entrepriseAdresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.admin = admin;
        this.password = password;
        this.idEntreprise = idEntreprise;
        this.entrepriseId = entrepriseId;
        this.entrepriseNom = entrepriseNom;
        this.entrepriseAdresse = entrepriseAdresse;
    }

    // Getters & Setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Integer idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public Integer getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(Integer entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public String getEntrepriseNom() {
        return entrepriseNom;
    }

    public void setEntrepriseNom(String entrepriseNom) {
        this.entrepriseNom = entrepriseNom;
    }

    public String getEntrepriseAdresse() {
        return entrepriseAdresse;
    }

    public void setEntrepriseAdresse(String entrepriseAdresse) {
        this.entrepriseAdresse = entrepriseAdresse;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", admin=" + admin +
                ", password='" + password + '\'' +
                ", idEntreprise=" + idEntreprise +
                ", entrepriseId=" + entrepriseId +
                ", entrepriseNom='" + entrepriseNom + '\'' +
                ", entrepriseAdresse='" + entrepriseAdresse + '\'' +
                '}';
    }
}
