package main.code.controller;

import main.code.dto.MenuDTO;
import main.code.dto.UserResponse;
import main.code.model.Entreprise;
import main.code.model.Menu;
import main.code.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    private final MenuService menuService;

    @Autowired
    public Controller(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    @PostMapping(path = "/addMenu")
    public String addNewMenu(@RequestBody MenuDTO dto) {
        return menuService.addNewMenu(
                dto.getNom(),
                dto.getImage(),
                dto.getPrix(),
                dto.getFkEntreprise());
    }

    @PutMapping(path = "/modifyMenu/{pk_menu}")
    public String modifyMenu(@PathVariable("pk_menu") Integer pkMenu, @RequestBody Menu menu) {
        return menuService.modifyMenu(pkMenu, menu.getNom(), menu.getImage(), menu.getPrix(), menu.getFkEntreprise());    
    }

    @DeleteMapping(path = "/deleteMenu")
    public String deleteMenu(@RequestParam Integer pkMenu) {
        return menuService.deleteMenu(pkMenu);
    }

    @GetMapping(path = "/getMenuById")
    public Iterable<MenuDTO> getAllMenusbyID(@RequestParam Integer fkEntreprise) {
        return menuService.findAllMenubyID(fkEntreprise);
    }

    @GetMapping(path = "/getMenu")
    public Iterable<MenuDTO> getAllMenus() {
        return menuService.findAllMenu();
    }
}
