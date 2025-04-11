package code.main.ctrl;

import code.main.beans.Entreprise;
import code.main.service.EntrepriseService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class Ctrl {

    private final EntrepriseService entrepriseService;

    public Ctrl(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;

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
    public ResponseEntity<String> deleteEntreprise(@PathVariable Integer id) {
        entrepriseService.deleteEntreprise(id);
        return ResponseEntity.ok("Entreprise supprimée avec succès !");
    }
}
