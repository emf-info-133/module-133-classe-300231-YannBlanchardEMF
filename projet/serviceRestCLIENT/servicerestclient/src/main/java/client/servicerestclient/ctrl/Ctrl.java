package client.servicerestclient.ctrl;

import client.servicerestclient.wrk.*;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import client.servicerestclient.beans.User;
import client.servicerestclient.dto.CommandeDTO;
import client.servicerestclient.dto.LoginDTO;
import client.servicerestclient.dto.RegisterDTO;
import client.servicerestclient.dto.UserDTO;

@RestController
public class Ctrl {

    ItfWrkCtrl wrk;

    public Ctrl() {
        wrk = new Wrk();
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginDTO dto) {
        return wrk.checkUser(dto.getLogin(), dto.getPassword());
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterDTO dto) {

        User user = new User();
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setFKEntreprise(dto.getIdEntreprise());

        if (wrk.addUser(user) != null) {
            return user;
        } else {
            return null;
        }

    }

    @PostMapping("/commande")
    public boolean ajouterCommande(@RequestBody CommandeDTO dto) {
        return wrk.ajouterCommande(dto.getLogin(), dto.getMenus(), dto.getTotal());
    }

    @GetMapping("/users")
    public ArrayList<User> getAllUsers() {
        return wrk.getAllUsers();
    }

    @GetMapping("/user")
    public User getUser(@RequestBody UserDTO dto) {
        return wrk.getUser(String.valueOf(dto.getId()));
    }
    
}
