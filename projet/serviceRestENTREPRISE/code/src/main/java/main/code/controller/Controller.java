package main.code.controller;

import main.code.dto.MenuDTO;
import main.code.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class Controller {

    private final MenuService menuService;

    @Autowired
    public Controller(MenuService menuService) {
        this.menuService = menuService;
    }

    // Handler pour GET
    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    @PostMapping(path = "/addMenu")
    public @ResponseBody String addNewMenu(@RequestParam String nom, @RequestParam Integer prix_unitaire,
            @RequestParam Integer fk_entreprise) {
        return menuService.addNewMenu(nom, prix_unitaire, fk_entreprise);
    }

    @PostMapping(path = "/modifyMenu")
    public @ResponseBody String modifyMenu(@RequestParam Integer pk_menu, @RequestParam String nom, @RequestParam Integer prix_unitaire,
            @RequestParam Integer fk_entreprise) {
        return menuService.modifyMenu(pk_menu, nom, prix_unitaire, fk_entreprise);
    }

    @PostMapping(path = "/deleteMenu")
    public @ResponseBody String deleteMenu(@RequestParam Integer pk_menu) {
        return menuService.deleteMenu(pk_menu);
    }

    @GetMapping(path = "/getMenu")
    public @ResponseBody Iterable<MenuDTO> getAllUsers() {
        // This returns a JSON or XML with the users
        return menuService.findAllMenu();
    }
}