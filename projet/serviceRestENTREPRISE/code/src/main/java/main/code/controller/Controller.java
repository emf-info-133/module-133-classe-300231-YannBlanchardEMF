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
    public String addNewMenu(String nom, Integer prix_unitaire, Integer userId) {
        return menuService.addNewMenu(nom, prix_unitaire, userId);
    }

    @Override
    public String modifyMenu(Integer pk_menu, String nom, Integer prix_unitaire, Integer userId) {
        return menuService.modifyMenu(pk_menu, nom, prix_unitaire, userId);
    }

    @Override
    public String deleteMenu(Integer pk_menu, Integer userId) {
        return menuService.deleteMenu(pk_menu, userId);
    }

    @Override
    public Iterable<MenuDTO> getAllUsers() {
        return menuService.findAllMenu();
    }

    @Override
    public Iterable<UserResponse> getAllUser() {
        return userService.findAllUser();
    }
}
