package code.main.ctrl;

import code.main.beans.Entreprise;
import code.main.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class Ctrl {

    private final EntrepriseService entrepriseService;

    @Autowired
    public Ctrl(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    // Ajouter une entreprise
    @PostMapping(path = "/addEntreprise")
    public Entreprise addEntreprise(@RequestBody Entreprise entreprise) {
        return entrepriseService.addEntreprise(entreprise);
    }

    // Récupérer toutes les entreprises
    @GetMapping(path = "/getEntreprises")
    public Iterable<Entreprise> getAllEntreprises() {
        return entrepriseService.getAllEntreprises();
    }

    // Récupérer une entreprise par ID
    @GetMapping(path = "/getEntreprises/{id}")
    public Optional<Entreprise> getEntrepriseById(@PathVariable Integer id) {
        return entrepriseService.getEntrepriseById(id);
    }

    // Modifier une entreprise
    @PutMapping(path = "/modifyEntreprise/{id}")
    public Entreprise modifyEntreprise(@PathVariable Integer id, @RequestBody Entreprise entreprise) {
        return entrepriseService.modifyEntreprise(id, entreprise.getNom(), entreprise.getAdresse());
    }

    // Supprimer une entreprise
    @DeleteMapping(path = "/deleteEntreprise/{id}")
    public void deleteEntreprise(@PathVariable Integer id) {
        entrepriseService.deleteEntreprise(id);
    }
}