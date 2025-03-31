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
    public @ResponseBody String addNewMenu(@RequestParam String name, @RequestParam Integer prixUnitaire,
            @RequestParam Integer fkEntreprise) {
        return menuService.addNewMenu(name, prixUnitaire, fkEntreprise);
    }

    @PostMapping(path = "/modifyMenu")
    public @ResponseBody String modifyMenu(@RequestParam Integer pkMenu, @RequestParam String name, @RequestParam Integer prixUnitaire,
            @RequestParam Integer fkEntreprise) {
        return menuService.modifyMenu(pkMenu, name, prixUnitaire, fkEntreprise);
    }

    @PostMapping(path = "/deleteMenu")
    public @ResponseBody String deleteMenu(@RequestParam Integer pkMenu) {
        return menuService.deleteMenu(pkMenu);
    }

    @GetMapping(path = "/getMenu")
    public @ResponseBody Iterable<MenuDTO> getAllUsers() {
        // This returns a JSON or XML with the users
        return menuService.findAllMenu();
    }
}