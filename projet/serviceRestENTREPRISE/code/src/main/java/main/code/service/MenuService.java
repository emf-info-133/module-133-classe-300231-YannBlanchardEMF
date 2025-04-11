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
    public String addNewMenu(String nom, String image, float prix, Integer fk_entreprise) {
        Menu newMenu = new Menu();
        newMenu.setNom(nom);
        newMenu.setImage(image);
        newMenu.setPrix(prix);
        newMenu.setFkEntreprise(fk_entreprise);
        menuRepository.save(newMenu);
        return "Saved";
    }

    @Transactional
    public String modifyMenu(Integer pk_menu, String nom, String image, float prix, Integer fkEntreprise) {
        Menu menu = menuRepository.findById(pk_menu).orElse(null);
        menu.setNom(nom);
        menu.setImage(image);
        menu.setPrix(prix);
        menu.setFkEntreprise(fkEntreprise);
        menuRepository.save(menu);
        return "Modified";
    }

    @Transactional
    public String deleteMenu(Integer pk_menu) {
        menuRepository.deleteById(pk_menu);
        return "Deleted";
    }

    @Transactional
    public List<MenuDTO> findAllMenubyID(Integer fkEntreprise) {
        List<Menu> menus = menuRepository.findByFkEntreprise(fkEntreprise);
        List<MenuDTO> menuDTOs = new ArrayList<>();
    
        for (Menu menu : menus) {
            MenuDTO menuDTO = new MenuDTO(
                menu.getPkMenu(),
                menu.getNom(),
                menu.getImage(),
                menu.getPrix(),
                menu.getFkEntreprise()
            );
            menuDTOs.add(menuDTO);
        }
    
        return menuDTOs;
    }

    @Transactional
    public List<MenuDTO> findAllMenu() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuDTO> menuDTOs = new ArrayList<>();
    
        for (Menu menu : menus) {
            MenuDTO menuDTO = new MenuDTO(
                menu.getPkMenu(),
                menu.getNom(),
                menu.getImage(),
                menu.getPrix(),
                menu.getFkEntreprise()
            );
            menuDTOs.add(menuDTO);
        }
    
        return menuDTOs;
    }
    
}
