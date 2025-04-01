package code.main.wrk;

import code.main.beans.Entreprise;
import code.main.service.EntrepriseService;

public class Wrk implements ItfWrkCtrl {

    private final EntrepriseService entrepriseService;

    public Wrk(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public boolean addEntreprise(String nom, String adresse) {
        Entreprise e = new Entreprise(nom, adresse);
        entrepriseService.addEntreprise(e);
        return true;
    }

    @Override
    public boolean deleteEntreprise(int id) {
        entrepriseService.deleteEntreprise(id);
        return true;
    }

    @Override
    public boolean modifyEntreprise(int id, String nom, String adresse) {
        entrepriseService.modifyEntreprise(id, nom, adresse);
        return true;
    }

    @Override
    public boolean addUser(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUser'");
    }

    @Override
    public boolean deleteUser(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public boolean modifyUser(int id, String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyUser'");
    }

    @Override
    public boolean login(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    // Les méthodes User restent vides ou à retirer si inutiles
}


