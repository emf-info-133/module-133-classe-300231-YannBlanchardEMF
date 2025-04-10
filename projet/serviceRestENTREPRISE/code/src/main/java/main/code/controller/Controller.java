package main.code.controller;

import main.code.dto.MenuDTO;
import main.code.dto.UserResponse;
import main.code.service.MenuService;
import main.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private final UserService userService;
    private final MenuService menuService;

    @Autowired
    public Controller(MenuService menuService, UserService userService) {
        this.menuService = menuService;
        this.userService = userService;
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
                dto.getFkEntreprise()
        );
    }

    @PostMapping(path = "/modifyMenu")
    public String modifyMenu(@RequestBody MenuDTO dto) {
        return menuService.modifyMenu(
                dto.getPkMenu(),
                dto.getNom(),
                dto.getImage(),
                dto.getPrix(),
                dto.getFkEntreprise()
        );
    }

    @PostMapping(path = "/deleteMenu")
    public String deleteMenu(@RequestBody MenuDTO dto) {
        return menuService.deleteMenu(
                dto.getPkMenu(),
                dto.getFkEntreprise()
        );
    }

    @GetMapping(path = "/getMenu")
    public Iterable<MenuDTO> getAllMenusbyID(@RequestParam Integer fkEntreprise) {
        return menuService.findAllMenu(fkEntreprise);
    }
}
