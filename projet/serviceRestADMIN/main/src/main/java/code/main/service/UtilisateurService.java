package code.main.service;


import code.main.beans.Utilisateur;
import code.main.dto.UserDTO;
import code.main.repository.EntrepriseRepository;
import code.main.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final EntrepriseRepository entrepriseRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, EntrepriseRepository entrepriseRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    public Utilisateur addUser(UserDTO dto) {
        Utilisateur user = new Utilisateur();
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setAdmin(dto.isAdmin());
        user.setPassword(dto.getPassword());

        if (dto.getIdEntreprise() != null) {
            entrepriseRepository.findById(dto.getIdEntreprise()).ifPresent(user::setEntreprise);
        }

        return utilisateurRepository.save(user);
    }

    public Utilisateur modifyUser(int id, UserDTO dto) {
        Optional<Utilisateur> optionalUser = utilisateurRepository.findById(id);
        if (optionalUser.isPresent()) {
            Utilisateur user = optionalUser.get();
            user.setNom(dto.getNom());
            user.setPrenom(dto.getPrenom());
            user.setAdmin(dto.isAdmin());
            user.setPassword(dto.getPassword());

            if (dto.getIdEntreprise() != null) {
                entrepriseRepository.findById(dto.getIdEntreprise()).ifPresent(user::setEntreprise);
            }

            return utilisateurRepository.save(user);
        }
        return null;
    }

    public void deleteUser(int id) {
        utilisateurRepository.deleteById(id);
    }

    public Iterable<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public boolean login(String nom, String password) {
        Utilisateur user = utilisateurRepository.findByNomAndPassword(nom, password);
        return user != null;
    }
}
