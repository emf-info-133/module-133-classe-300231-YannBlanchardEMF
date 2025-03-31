package main.code.repository;

import org.springframework.data.repository.CrudRepository;
import main.code.model.Entreprise;

public interface EntrepriseRepository extends CrudRepository<Entreprise, Integer> {

}
