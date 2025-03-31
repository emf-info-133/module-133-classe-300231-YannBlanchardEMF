package code.main.wrk;

public interface ItfWrkCtrl {

    // Méthodes liées à l'entreprise
    boolean addEntreprise(String nom, String adresse);
    boolean deleteEntreprise(int id);
    boolean modifyEntreprise(int id, String nom, String adresse);

    // Méthodes liées à l'utilisateur (au cas où tu veux ajouter après)
    boolean addUser(String username, String password);
    boolean deleteUser(int id);
    boolean modifyUser(int id, String username, String password);

    // Autres méthodes possibles (connexion, vérification)
    boolean login(String username, String password);
}
