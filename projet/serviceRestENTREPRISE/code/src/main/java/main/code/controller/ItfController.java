package main.code.controller;

import main.code.dto.MenuDTO;
import main.code.dto.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ItfController {

    @GetMapping("/")
    String getNothing();

    @PostMapping(path = "/addMenu")
    @ResponseBody
    String addNewMenu(@RequestParam String nom,
                      @RequestParam String image,
                      @RequestParam float prix_unitaire,
                      @RequestParam int fk_entreprise);

    @PostMapping(path = "/modifyMenu")
    @ResponseBody
    String modifyMenu(@RequestParam int pk_menu,
                      @RequestParam String nom,
                      @RequestParam String image,
                      @RequestParam float prix_unitaire,
                      @RequestParam int fk_entreprise);

    @PostMapping(path = "/deleteMenu")
    @ResponseBody
    String deleteMenu(@RequestParam int pk_menu,
                      @RequestParam int fk_entreprise);

    @GetMapping(path = "/getMenu")
    @ResponseBody
    Iterable<MenuDTO> getAllMenus();

    @GetMapping(path = "/getUser")
    @ResponseBody
    Iterable<UserResponse> getAllUser();
}
