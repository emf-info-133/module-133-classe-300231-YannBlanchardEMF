package code.main.repository;

import org.springframework.data.repository.CrudRepository;

import code.main.beans.Entreprise;

public interface EntrepriseRepository extends CrudRepository<Entreprise, Integer> {
    // Spring implémente automatiquement les méthodes CRUD (save, delete, findById, findAll...)
}
