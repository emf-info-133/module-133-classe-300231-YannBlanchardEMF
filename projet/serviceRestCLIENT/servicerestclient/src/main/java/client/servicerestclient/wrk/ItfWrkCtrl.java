package client.servicerestclient.wrk;

import java.util.ArrayList;

import client.servicerestclient.beans.Menu;
import client.servicerestclient.beans.User;

public interface ItfWrkCtrl {
    User checkUser(String login , String mdp);
    User addUser(User user);
    boolean ajouterCommande(String login, ArrayList<Menu> menus, float total);
    ArrayList<User> getAllUsers();

}
