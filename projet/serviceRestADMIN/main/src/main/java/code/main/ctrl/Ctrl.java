package code.main.ctrl;

import code.main.beans.Entreprise;
import code.main.beans.Utilisateur;
import code.main.dto.UserDTO;
import code.main.service.EntrepriseService;
import code.main.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class Ctrl {

    private final EntrepriseService entrepriseService;
    private final UtilisateurService utilisateurService;

    public Ctrl(EntrepriseService entrepriseService, UtilisateurService utilisateurService) {
        this.entrepriseService = entrepriseService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    // Entreprise ----------------------------------

    @PostMapping("/addEntreprise")
    public Entreprise addEntreprise(@RequestBody Entreprise entreprise) {
        return entrepriseService.addEntreprise(entreprise);
    }

    @GetMapping("/getEntreprises")
    public Iterable<Entreprise> getAllEntreprises() {
        return entrepriseService.getAllEntreprises();
    }

    @GetMapping("/getEntreprises/{id}")
    public Optional<Entreprise> getEntrepriseById(@PathVariable Integer id) {
        return entrepriseService.getEntrepriseById(id);
    }

    @PutMapping("/modifyEntreprise/{id}")
    public Entreprise modifyEntreprise(@PathVariable Integer id, @RequestBody Entreprise entreprise) {
        return entrepriseService.modifyEntreprise(id, entreprise.getNom(), entreprise.getAdresse());
    }

    @DeleteMapping("/deleteEntreprise/{id}")
    public void deleteEntreprise(@PathVariable Integer id) {
        entrepriseService.deleteEntreprise(id);
    }

    // Utilisateur (Admin) --------------------------

    @PostMapping("/addUser")
    public Utilisateur addUser(@RequestBody UserDTO dto) {
        return utilisateurService.addUser(dto);
    }

    @PutMapping("/modifyUser/{id}")
    public Utilisateur modifyUser(@PathVariable Integer id, @RequestBody UserDTO dto) {
        return utilisateurService.modifyUser(id, dto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Integer id) {
        utilisateurService.deleteUser(id);
    }

    @GetMapping("/getUsers")
    public Iterable<Utilisateur> getUsers() {
        return utilisateurService.getAllUsers();
    }

    @PostMapping("/loginUser")
    public boolean loginUser(@RequestBody UserDTO dto) {
        return utilisateurService.login(dto.getNom(), dto.getPassword());
    }
}
