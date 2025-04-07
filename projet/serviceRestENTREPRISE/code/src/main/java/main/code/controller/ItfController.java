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
    String addNewMenu(@RequestParam String nom, @RequestParam Integer prix_unitaire,
                      @RequestParam Integer userId);

    @PostMapping(path = "/modifyMenu")
    @ResponseBody
    String modifyMenu(@RequestParam Integer pk_menu, @RequestParam String nom,
                      @RequestParam Integer prix_unitaire, @RequestParam Integer userId);

    @PostMapping(path = "/deleteMenu")
    @ResponseBody
    String deleteMenu(@RequestParam Integer pk_menu, @RequestParam Integer userId);

    @GetMapping(path = "/getMenu")
    @ResponseBody
    Iterable<MenuDTO> getAllUsers();

    @GetMapping(path = "/getUser")
    @ResponseBody
    Iterable<UserResponse> getAllUser();
}
