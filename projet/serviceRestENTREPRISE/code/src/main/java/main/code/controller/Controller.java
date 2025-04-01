package main.code.controller;

import main.code.dto.MenuDTO;
import main.code.dto.UserResponse;
import main.code.service.MenuService;
import main.code.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class Controller {
    private final UserService userService;
    private final MenuService menuService;

    @Autowired
    public Controller(MenuService menuService, UserService userService) {
        this.menuService = menuService;
        this.userService = userService;
    }

    // Handler pour GET
    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    @PostMapping(path = "/addMenu")
    public @ResponseBody String addNewMenu(@RequestParam String nom, @RequestParam Integer prix_unitaire,
                                           @RequestParam Integer userId) {
        return menuService.addNewMenu(nom, prix_unitaire, userId);
    }
    
    @PostMapping(path = "/modifyMenu")
    public @ResponseBody String modifyMenu(@RequestParam Integer pk_menu, @RequestParam String nom,
                                           @RequestParam Integer prix_unitaire, @RequestParam Integer userId) {
        return menuService.modifyMenu(pk_menu, nom, prix_unitaire, userId);
    }
    
    @PostMapping(path = "/deleteMenu")
    public @ResponseBody String deleteMenu(@RequestParam Integer pk_menu, @RequestParam Integer userId) {
        return menuService.deleteMenu(pk_menu, userId);
    }

    @GetMapping(path = "/getMenu")
    public @ResponseBody Iterable<MenuDTO> getAllUsers() {
        // This returns a JSON or XML with the users
        return menuService.findAllMenu();
    }

    @GetMapping(path = "/getUser")
    public @ResponseBody Iterable<UserResponse> getAllUser() {
        // This returns a JSON or XML with the users
        return userService.findAllUser();
    }

}