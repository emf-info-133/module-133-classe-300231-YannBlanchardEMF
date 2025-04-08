package main.code.controller;

import main.code.dto.MenuDTO;
import main.code.dto.UserResponse;
import main.code.service.MenuService;
import main.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller implements ItfController {

    private final UserService userService;
    private final MenuService menuService;

    @Autowired
    public Controller(MenuService menuService, UserService userService) {
        this.menuService = menuService;
        this.userService = userService;
    }

    @Override
    public String getNothing() {
        return "";
    }

    @Override
    public String addNewMenu(String nom, String image, float prix_unitaire, int fk_entreprise) {
        return menuService.addNewMenu(nom, image, prix_unitaire, fk_entreprise);
    }

    @Override
    public String modifyMenu(int pk_menu, String nom, String image, float prix_unitaire, int fk_entreprise) {
        return menuService.modifyMenu(pk_menu, nom, image, prix_unitaire, fk_entreprise);
    }

    @Override
    public String deleteMenu(int pk_menu, int fk_entreprise) {
        return menuService.deleteMenu(pk_menu, fk_entreprise);
    }

    @Override
    public Iterable<MenuDTO> getAllMenus() {
        return menuService.findAllMenu();
    }

    @Override
    public Iterable<UserResponse> getAllUser() {
        return userService.findAllUser();
    }
}
