package main.code.service;

import main.code.dto.UserResponse;
import main.code.dto.MenuDTO;
import main.code.model.Entreprise;
import main.code.model.Menu;
import main.code.repository.EntrepriseRepository;
import main.code.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public MenuService(MenuRepository menuRepository,
                       EntrepriseRepository entrepriseRepository,
                       RestTemplate restTemplate) {
        this.menuRepository = menuRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.restTemplate = restTemplate;
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
    public String addNewMenu(String nom, Integer prix_unitaire, Integer userId) {
        Integer fkEntreprise = getEntrepriseIdFromUser(userId);
        if (fkEntreprise == null) return "unauthorized: user not linked to entreprise";

        Entreprise entreprise = entrepriseRepository.findById(fkEntreprise).orElse(null);
        if (entreprise == null) return "entreprise not found";

        Menu newMenu = new Menu();
        newMenu.setNom(nom);
        newMenu.setPrixUnitaire(prix_unitaire);
        newMenu.setEntreprise(entreprise);
        menuRepository.save(newMenu);
        return "Saved";
    }

    @Transactional
    public String modifyMenu(Integer pk_menu, String nom, Integer prix_unitaire, Integer userId) {
        Menu menu = menuRepository.findById(pk_menu).orElse(null);
        if (menu == null) return "menu not found";

        Integer fkEntreprise = getEntrepriseIdFromUser(userId);
        if (fkEntreprise == null || !menu.getEntreprise().getPkEntreprise().equals(fkEntreprise)) {
            return "unauthorized: can't modify this menu";
        }

        menu.setNom(nom);
        menu.setPrixUnitaire(prix_unitaire);
        menuRepository.save(menu);
        return "Modified";
    }

    @Transactional
    public String deleteMenu(Integer pk_menu, Integer userId) {
        Menu menu = menuRepository.findById(pk_menu).orElse(null);
        if (menu == null) return "menu not found";

        Integer fkEntreprise = getEntrepriseIdFromUser(userId);
        if (fkEntreprise == null || !menu.getEntreprise().getPkEntreprise().equals(fkEntreprise)) {
            return "unauthorized: can't delete this menu";
        }

        menuRepository.delete(menu);
        return "Deleted";
    }

    private Integer getEntrepriseIdFromUser(Integer userId) {
        try {
            String url = "http://api-gateway/client/api/user/" + userId;
            ResponseEntity<UserResponse> response = restTemplate.getForEntity(url, UserResponse.class);
            UserResponse user = response.getBody();
            return (user != null) ? user.getFkEntreprise() : null;
        } catch (Exception e) {
            return null;
        }
    }
}
