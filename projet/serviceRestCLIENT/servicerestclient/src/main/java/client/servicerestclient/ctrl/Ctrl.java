package client.servicerestclient.ctrl;

import client.servicerestclient.wrk.*;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import client.servicerestclient.beans.Menu;
import client.servicerestclient.beans.User;
import client.servicerestclient.dto.CommandeDTO;
import client.servicerestclient.dto.LoginDTO;
import client.servicerestclient.dto.RegisterDTO;

@RestController
public class Ctrl {

    ItfWrkCtrl wrk;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String menuServiceUrl = "http://localhost:8080"; // ou docker host

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
        user.setFKEntreprise(null);

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
    public ResponseEntity<User> getUser(@RequestParam int id) {
        User user = wrk.getUser(String.valueOf(id));
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (wrk.addUser(user) != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deleted = wrk.deleteUser(id);

        if (deleted) {
            return ResponseEntity.ok("Utilisateur supprimé avec succès");
        } else {
            return ResponseEntity.status(404).body("Utilisateur introuvable");
        }
    }

    @PutMapping("/modifyUser/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable int id, @RequestBody User user) {
        user.setPK(id); // très important pour l’update
        User updatedUser = wrk.modifyUser(user);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

}
