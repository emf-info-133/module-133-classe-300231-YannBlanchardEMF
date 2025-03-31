package main.code.service;
import main.code.dto.MenuDTO;
import main.code.model.Entreprise;
import main.code.model.Menu;
import main.code.repository.EntrepriseRepository;
import main.code.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, EntrepriseRepository entrepriseRepository) {
        this.menuRepository = menuRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    public Iterable<MenuDTO> findAllMenu() {
        Iterable<Menu> menus = menuRepository.findAll();
        List<MenuDTO> menuDTOs = new ArrayList<>();
        for (Menu menu : menus) {
            MenuDTO menuDTO = new MenuDTO(
                menu.getPkMenu(),
                menu.getNom(),
                menu.getPrixUnitaire());
                menuDTOs.add(menuDTO);
        }
        return menuDTOs;
    }
    @Transactional
    public String addNewMenu(String nom, Integer prix_unitaire, Integer pk_entreprise) {
        Entreprise entreprise = entrepriseRepository.findById(pk_entreprise).orElse(null);
        if (entreprise == null) {
            return "entreprise not found";
        }
        Menu newMenu = new Menu();
        newMenu.setNom(nom);
        newMenu.setPrixUnitaire(prix_unitaire);
        newMenu.setEntreprise(entreprise);
        menuRepository.save(newMenu);
        return "Saved";
    }

    @Transactional
    public String modifyMenu(Integer Pk_Menu, String nom, Integer prix_unitaire, Integer pk_entreprise) {
        Menu menu = menuRepository.findById(Pk_Menu).orElse(null);
        if (menu == null) {
            return "menu not found";
        }

        Entreprise entreprise = entrepriseRepository.findById(pk_entreprise).orElse(null);
        if (entreprise == null) {
            return "entreprise not found";
        }

        menu.setNom(nom);
        menu.setPrixUnitaire(prix_unitaire);
        menu.setEntreprise(entreprise);

        menuRepository.save(menu);
        return "Modified";
    }

    @Transactional
    public String deleteMenu(Integer pk_menu) {
        Menu menu = menuRepository.findById(pk_menu).orElse(null);
        if (menu == null) {
            return "menu not found";
    }
        menuRepository.delete(menu);
        return "Deleted";
}
}
