package code.main.repository;

import code.main.beans.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
    Utilisateur findByNomAndPassword(String nom, String password);
}
