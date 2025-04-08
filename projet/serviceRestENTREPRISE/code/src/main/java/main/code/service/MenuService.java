package main.code.service;

import jakarta.transaction.Transactional;
import main.code.dto.MenuDTO;
import main.code.dto.UserResponse;
import main.code.model.Menu;
import main.code.model.User;
import main.code.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Transactional
    public String addNewMenu(String nom, String image, float prix_unitaire, int fk_entreprise) {
        Menu newMenu = new Menu();
        newMenu.setNom(nom);
        newMenu.setImage(image);
        newMenu.setPrix(prix_unitaire);
        newMenu.setFKEntreprise(fk_entreprise);
        menuRepository.save(newMenu);
        return "Saved";
    }

    @Transactional
    public String modifyMenu(int pk_menu, String nom, String image, float prix_unitaire, int fk_entreprise) {
        Menu menu = menuRepository.findById(pk_menu).orElse(null);
        if (menu == null) return "menu not found";

        menu.setNom(nom);
        menu.setImage(image);
        menu.setPrix(prix_unitaire);
        menu.setFKEntreprise(fk_entreprise);
        menuRepository.save(menu);
        return "Modified";
    }

    @Transactional
    public String deleteMenu(int pk_menu, int fk_entreprise) {
        Menu menu = menuRepository.findById(pk_menu).orElse(null);
        if (menu == null) return "menu not found";

        menuRepository.delete(menu);
        return "Deleted";
    }

    @Transactional
   public Iterable<MenuDTO> findAllMenu() {
        Iterable<Menu> menus = menuRepository.findAll();
        List<MenuDTO> menuDTOs = new ArrayList<>();
        for (Menu menu : menus) {
            MenuDTO menuDTO = new MenuDTO(
                menu.getPkMenu(),
                menu.getNom(),
                menu.getImage(),
                menu.getPrix(),
                menu.getFKEntreprise());
                menuDTOs.add(menuDTO);
        }
        return menuDTOs;
    }
}
