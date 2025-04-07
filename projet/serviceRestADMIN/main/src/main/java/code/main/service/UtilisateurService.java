package code.main.service;

import code.main.beans.User;
import code.main.dto.UserDTO;
import code.main.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public User addUser(UserDTO dto) {
        User user = new User();
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setLogin(dto.getLogin());
        user.setAdmin(dto.isAdmin());
        user.setPassword(dto.getPassword());

        if (dto.getIdEntreprise() != null) {
            user.setFKEntreprise(dto.getIdEntreprise());
        }

        return utilisateurRepository.save(user);
    }

    public User modifyUser(int id, UserDTO dto) {
        Optional<User> optionalUser = utilisateurRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setNom(dto.getNom());
            user.setPrenom(dto.getPrenom());
            user.setLogin(dto.getLogin());
            user.setAdmin(dto.isAdmin());
            user.setPassword(dto.getPassword());

            if (dto.getIdEntreprise() != null) {
                user.setFKEntreprise(dto.getIdEntreprise());
            }

            return utilisateurRepository.save(user);
        }
        return null;
    }

    public void deleteUser(int id) {
        utilisateurRepository.deleteById(id);
    }

    public Iterable<User> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public boolean login(String login, String password) {
        User user = utilisateurRepository.findByNomAndPassword(login, password);
        return user != null;
    }
}
