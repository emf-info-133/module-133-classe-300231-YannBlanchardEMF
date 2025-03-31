package client.servicerestclient.ctrl;

import client.servicerestclient.wrk.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import client.servicerestclient.beans.User;
import client.servicerestclient.dto.CommandeDTO;
import client.servicerestclient.dto.LoginDTO;
import client.servicerestclient.dto.RegisterDTO;

@RestController
public class Ctrl {

    ItfWrkCtrl wrk;

    public Ctrl() {
        wrk = new Wrk(); 
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginDTO dto) {
        return wrk.checkUser(dto.getLogin());
    }

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterDTO dto) {
        User user = new User();
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setIdEntreprise(dto.getIdEntreprise());
        return wrk.addUser(user);
    }

    @PostMapping("/commande")
    public boolean ajouterCommande(@RequestBody CommandeDTO dto) {
        return wrk.ajouterCommande(dto.getLogin(), dto.getMenus(), dto.getTotal());
    }
}
