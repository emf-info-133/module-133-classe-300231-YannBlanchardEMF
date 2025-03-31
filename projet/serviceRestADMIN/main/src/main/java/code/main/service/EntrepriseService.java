package code.main.service;

import code.main.beans.Entreprise;
import code.main.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    // Ajouter une entreprise
    public Entreprise addEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    // Supprimer une entreprise
    public void deleteEntreprise(Integer id) {
        entrepriseRepository.deleteById(id);
    }

    // Modifier une entreprise
    public Entreprise modifyEntreprise(Integer id, String nom, String adresse) {
        Optional<Entreprise> entrepriseExistante = entrepriseRepository.findById(id);
        if (entrepriseExistante.isPresent()) {
            Entreprise entreprise = entrepriseExistante.get();
            entreprise.setNom(nom);
            entreprise.setAdresse(adresse);
            return entrepriseRepository.save(entreprise);
        }
        return null;
    }

    // Obtenir toutes les entreprises
    public Iterable<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    // Obtenir une entreprise par ID
    public Optional<Entreprise> getEntrepriseById(Integer id) {
        return entrepriseRepository.findById(id);
    }
}

