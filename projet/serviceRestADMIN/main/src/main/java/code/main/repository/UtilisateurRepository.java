package code.main.repository;

import code.main.beans.User;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<User, Integer> {
    User findByNomAndPassword(String nom, String password);
}
