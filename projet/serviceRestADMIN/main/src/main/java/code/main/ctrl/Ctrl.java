package code.main.ctrl;

import code.main.beans.Entreprise;
import code.main.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Ctrl {

    @Autowired
    private EntrepriseService entrepriseService;

    // Ajouter une entreprise
    @PostMapping("/entreprises")
    public Entreprise addEntreprise(@RequestBody Entreprise entreprise) {
        return entrepriseService.addEntreprise(entreprise);
    }

    // Récupérer toutes les entreprises
    @GetMapping("/entreprises")
    public Iterable<Entreprise> getAllEntreprises() {
        return entrepriseService.getAllEntreprises();
    }

    // Récupérer une entreprise par ID
    @GetMapping("/entreprises/{id}")
    public Optional<Entreprise> getEntrepriseById(@PathVariable Integer id) {
        return entrepriseService.getEntrepriseById(id);
    }

    // Modifier une entreprise
    @PutMapping("/entreprises/{id}")
    public Entreprise modifyEntreprise(@PathVariable Integer id, @RequestBody Entreprise entreprise) {
        return entrepriseService.modifyEntreprise(id, entreprise.getNom(), entreprise.getAdresse());
    }

    // Supprimer une entreprise
    @DeleteMapping("/entreprises/{id}")
    public void deleteEntreprise(@PathVariable Integer id) {
        entrepriseService.deleteEntreprise(id);
    }
}