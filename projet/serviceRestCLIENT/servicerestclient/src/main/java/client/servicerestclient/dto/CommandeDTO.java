package client.servicerestclient.dto;

import client.servicerestclient.beans.Menu;
import java.util.ArrayList;

public class CommandeDTO {
    private String login;
    private ArrayList<Menu> menus;
    private float total;

    public CommandeDTO() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
