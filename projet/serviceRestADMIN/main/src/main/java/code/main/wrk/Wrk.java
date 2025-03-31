package code.main.wrk;

public class Wrk implements ItfWrkCtrl {

    @Override
    public boolean addEntreprise(String nom, String adresse) {
        return false;
    }

    @Override
    public boolean deleteEntreprise(int id) {
        return false;
    }

    @Override
    public boolean modifyEntreprise(int id, String nom, String adresse) {
        return false;
    }

    @Override
    public boolean addUser(String username, String password) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public boolean modifyUser(int id, String username, String password) {
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }
}

