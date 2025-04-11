package client.servicerestclient.wrk;

import java.util.ArrayList;
import client.servicerestclient.beans.Menu;
import client.servicerestclient.beans.User;

public class Wrk implements ItfWrkCtrl {
    private WrkDB wrkDB;

    public Wrk() {
        wrkDB = new WrkDB();

    }

    @Override
    public User checkUser(String login, String mdp) {
        return wrkDB.readUser(login, mdp);
    }

    @Override
    public User addUser(User user) {
        return wrkDB.addUser(user);
    }

    @Override
    public boolean ajouterCommande(String login, ArrayList<Menu> menus, float total) {
        return wrkDB.ajouterCommande(login, menus, total);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return wrkDB.getAllUsers();
    }

    @Override
    public User getUser(String pk) {
        
        return wrkDB.getUser(pk);
    }

    @Override
    public boolean deleteUser(int pk) {
        return wrkDB.deleteUser(pk);
    }

    @Override
    public User modifyUser(User user) {
        return wrkDB.modifyUser(user);
    }

}
