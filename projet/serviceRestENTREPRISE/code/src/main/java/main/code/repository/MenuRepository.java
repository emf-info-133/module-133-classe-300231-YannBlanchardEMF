package main.code.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import main.code.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByFKEntreprise(Integer fkEntreprise);
}

