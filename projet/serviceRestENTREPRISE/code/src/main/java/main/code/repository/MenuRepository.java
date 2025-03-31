package main.code.repository;

import org.springframework.data.repository.CrudRepository;
import main.code.model.Menu;

public interface MenuRepository extends CrudRepository<Menu, Integer> {

}
