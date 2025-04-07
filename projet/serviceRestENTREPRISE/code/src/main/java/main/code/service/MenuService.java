package main.code.service;

import main.code.dto.MenuDTO;
import main.code.dto.UserResponse;
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
            menuDTOs.add(new MenuDTO(menu.getPkMenu(), menu.getNom(), menu.getPrix()));
        }
        return menuDTOs;
    }

    @Transactional
    public String addNewMenu(String nom, Integer prix_unitaire, Integer userId) {
        Integer fkEntreprise = getEntrepriseIdFromUser(userId);
        if (fkEntreprise == null) return "unauthorized: user not linked to an entreprise";

        Entreprise entreprise = entrepriseRepository.findById(fkEntreprise).orElse(null);
        if (entreprise == null) return "entreprise not found";

        // Vérifie si un menu avec ce nom existe déjà pour cette entreprise
        for (Menu m : menuRepository.findAll()) {
            if (m.getNom().equalsIgnoreCase(nom) && m.getFKEntreprise() == fkEntreprise) {
                return "menu already exists for this entreprise";
            }
        }

        Menu newMenu = new Menu();
        newMenu.setNom(nom);
        newMenu.setPrix(prix_unitaire);
        newMenu.setFKEntreprise(entreprise.getPkEntreprise());
        menuRepository.save(newMenu);
        return "Saved";
    }

    @Transactional
    public String modifyMenu(Integer pk_menu, String nom, Integer prix_unitaire, Integer userId) {
        if (pk_menu == null) return "no menu selected";

        Menu menu = menuRepository.findById(pk_menu).orElse(null);
        if (menu == null) return "menu not found";

        Integer fkEntreprise = getEntrepriseIdFromUser(userId);
        if (fkEntreprise == null || menu.getFKEntreprise() != fkEntreprise) {
            return "unauthorized: you can't modify this menu";
        }

        menu.setNom(nom);
        menu.setPrix(prix_unitaire);
        menuRepository.save(menu);
        return "Modified";
    }

    @Transactional
    public String deleteMenu(Integer pk_menu, Integer userId) {
        if (pk_menu == null) return "no menu selected";

        Menu menu = menuRepository.findById(pk_menu).orElse(null);
        if (menu == null) return "menu not found";

        Integer fkEntreprise = getEntrepriseIdFromUser(userId);
        if (fkEntreprise == null || menu.getFKEntreprise() != fkEntreprise) {
            return "unauthorized: you can't delete this menu";
        }

        menuRepository.delete(menu);
        return "Deleted";
    }

    private Integer getEntrepriseIdFromUser(Integer userId) {
        try {
            String url = "http://localhost:8081/user?PK_Users=" + userId;
            ResponseEntity<UserResponse> response = restTemplate.getForEntity(url, UserResponse.class);
            UserResponse user = response.getBody();
            return (user != null) ? user.getFkEntreprise() : null;
        } catch (Exception e) {
            return null;
        }
    }
}
