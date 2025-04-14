package main.src.ctrl;

import jakarta.servlet.http.HttpSession;
import main.src.beans.Entreprise;
import main.src.beans.Menu;
import main.src.beans.User;
import main.src.dto.ClientDTO;
import main.src.dto.EntrepriseDTO;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String clientBaseUrl = "http://client:8081";
    private final String entrepriseBaseUrl = "http://entreprise:8082";
    private final String adminBaseUrl = "http://admin:8083";

    // ---------------------- AUTH ----------------------

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody ClientDTO dto, HttpSession session) {
        // Appel au service client pour authentifier l'utilisateur
        ResponseEntity<User> response = restTemplate.postForEntity(clientBaseUrl + "/login", dto, User.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            User user = response.getBody();

            // Stockage des infos utilisateur dans la session
            session.setAttribute("id", user.getPk());
            session.setAttribute("admin", user.isAdmin());
            session.setAttribute("fkEntreprise", user.getFKEntreprise());

            System.out.println("fk entreprise : " + user.getFKEntreprise());

            // Appel au service entreprise pour récupérer l'entreprise liée
            if (user.getFKEntreprise() != null) {
                try {
                    ResponseEntity<Entreprise> entrepriseResponse = restTemplate.getForEntity(
                            adminBaseUrl + "/getEntreprises/" + user.getFKEntreprise(),
                            Entreprise.class);

                    if (entrepriseResponse.getStatusCode().is2xxSuccessful()) {
                        session.setAttribute("entreprise", entrepriseResponse.getBody());
                    }

                } catch (Exception e) {
                    System.out.println("Erreur récupération entreprise : " + e.getMessage());
                }
            }
        }

        return response;
    }

    @GetMapping("/myEntreprise")
    public ResponseEntity<?> getMyEntreprise(HttpSession session) {
        Entreprise entreprise = (Entreprise) session.getAttribute("entreprise");

        if (entreprise == null) {
            return ResponseEntity.status(404).body("Aucune entreprise liée à l'utilisateur connecté.");
        }

        return ResponseEntity.ok(entreprise);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody ClientDTO dto, HttpSession session) {
        ResponseEntity<User> response = restTemplate.postForEntity(clientBaseUrl + "/register", dto, User.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            User user = response.getBody();
            session.setAttribute("id", user.getPk());
            session.setAttribute("admin", user.isAdmin());
            session.setAttribute("fkEntreprise", null);
        }
        return response;
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Déconnexion réussie.");
    }

    // ---------------------- CLIENT ----------------------

    @PostMapping("/commande")
    public ResponseEntity<String> commander(@RequestBody ClientDTO dto, HttpSession session) {
        // Vérifie que l'utilisateur est connecté
        if (session.getAttribute("id") == null) {
            return ResponseEntity.status(401).body("Non connecté !");
        }

        // Forward direct vers le microservice client
        return restTemplate.postForEntity(clientBaseUrl + "/commande", dto, String.class);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserById(@RequestParam int id) {
        return restTemplate.getForEntity(clientBaseUrl + "/user?id=" + id, User.class);
    }

    @GetMapping("/users")
    public ResponseEntity<User[]> getAllUsers() {
        return restTemplate.getForEntity(clientBaseUrl + "/users", User[].class);
    }

    // ---------------------- ENTREPRISE ----------------------

    @PostMapping("/addMenu")
    public ResponseEntity<String> addMenu(@RequestBody Menu dto, HttpSession session) {
        Integer sessionFk = (Integer) session.getAttribute("fkEntreprise");
        if (sessionFk == null) {
            return ResponseEntity.status(403).body("Accès refusé");
        }

        dto.setFkEntreprise(sessionFk);

        return restTemplate.postForEntity(entrepriseBaseUrl + "/addMenu", dto, String.class);
    }

    @PutMapping("/modifyMenu/{pk_menu}")
    public ResponseEntity<String> modifyMenu(
            @PathVariable Integer pk_menu,
            @RequestBody Menu dto,
            HttpSession session) {

        // 🔐 fkEntreprise uniquement via la session
        Integer sessionFk = (Integer) session.getAttribute("fkEntreprise");
        if (sessionFk == null) {
            return ResponseEntity.status(403).body("Accès refusé (non connecté)");
        }

        // 🔍 On vérifie que le menu existe et qu’il appartient à la même entreprise
        String url = entrepriseBaseUrl + "/getMenuByPK?pk=" + pk_menu;
        ResponseEntity<Menu[]> response = restTemplate.getForEntity(url, Menu[].class);
        Menu[] menus = response.getBody();

        if (menus == null || menus.length == 0) {
            return ResponseEntity.status(404).body("Menu introuvable");
        }

        Menu menuOriginal = menus[0];
        if (!sessionFk.equals(menuOriginal.getFkEntreprise())) {
            return ResponseEntity.status(403).body("Ce menu n’appartient pas à votre entreprise");
        }

        // ✅ fkEntreprise validée → on ignore celle du JSON et on met celle de session
        dto.setFkEntreprise(sessionFk);

        HttpEntity<Menu> requestEntity = new HttpEntity<>(dto);
        return restTemplate.exchange(
                entrepriseBaseUrl + "/modifyMenu/" + pk_menu,
                HttpMethod.PUT,
                requestEntity,
                String.class);
    }

    @DeleteMapping("/deleteMenu/{pk_menu}")
    public ResponseEntity<String> deleteMenu(@PathVariable Integer pk_menu, HttpSession session) {
        Integer sessionFk = (Integer) session.getAttribute("fkEntreprise");
        if (sessionFk == null) {
            return ResponseEntity.status(403).body("Accès refusé");
        }

        ResponseEntity<Menu[]> response = restTemplate.getForEntity(
                entrepriseBaseUrl + "/getMenuByPK?pk=" + pk_menu, Menu[].class);
        Menu[] menus = response.getBody();

        if (menus == null || menus.length == 0 || !sessionFk.equals(menus[0].getFkEntreprise())) {
            return ResponseEntity.status(403).body("Accès refusé");
        }

        restTemplate.delete(entrepriseBaseUrl + "/deleteMenu/" + pk_menu);
        return ResponseEntity.ok("Menu supprimé");
    }

    @GetMapping("/getMenuById")
    public ResponseEntity<Menu[]> getMenuByID(@RequestParam("fk_entreprise") Integer fk_entreprise) {
        return restTemplate.getForEntity(
                entrepriseBaseUrl + "/getMenuById?fkEntreprise=" + fk_entreprise,
                Menu[].class);
    }

    @GetMapping("/getMenu")
    public ResponseEntity<Menu[]> getMenu() {
        return restTemplate.getForEntity(
                entrepriseBaseUrl + "/getMenu", Menu[].class);
    }

    @GetMapping("/getMenuByPK")
    public ResponseEntity<Menu[]> getMenuByPK(@RequestParam List<Integer> pk) {
        StringBuilder urlBuilder = new StringBuilder(entrepriseBaseUrl + "/getMenuByPK?");
        for (Integer id : pk) {
            urlBuilder.append("pk=").append(id).append("&");
        }

        String url = urlBuilder.toString();

        return restTemplate.getForEntity(url, Menu[].class);
    }

    // ---------------------- ADMIN ----------------------

    private boolean isAdmin(HttpSession session) {
        return Boolean.TRUE.equals(session.getAttribute("admin")) && session.getAttribute("id") != null;
    }

    @PostMapping("/addEntreprise")
    public ResponseEntity<String> addEntreprise(@RequestBody Entreprise dto, HttpSession session) {
        if (!isAdmin(session))
            return ResponseEntity.status(403).body("Accès refusé");
        return restTemplate.postForEntity(adminBaseUrl + "/addEntreprise", dto, String.class);
    }

    @PutMapping("/modifyEntreprise/{id}")
    public ResponseEntity<String> modifyEntreprise(@PathVariable Integer id, @RequestBody Entreprise dto,
            HttpSession session) {
        if (!isAdmin(session))
            return ResponseEntity.status(403).body("Accès refusé");
        HttpEntity<Entreprise> requestEntity = new HttpEntity<>(dto);
        return restTemplate.exchange(adminBaseUrl + "/modifyEntreprise/" + id, HttpMethod.PUT, requestEntity,
                String.class);
    }

    @DeleteMapping("/deleteEntreprise/{id}")
    public ResponseEntity<String> deleteEntreprise(@PathVariable Integer id, HttpSession session) {
        if (!isAdmin(session))
            return ResponseEntity.status(403).body("Accès refusé");
        restTemplate.delete(adminBaseUrl + "/deleteEntreprise/" + id);
        return ResponseEntity.ok("Entreprise supprimée");
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User dto, HttpSession session) {
        if (!isAdmin(session))
            return ResponseEntity.status(403).body("Accès refusé");
        return restTemplate.postForEntity(clientBaseUrl + "/addUser", dto, String.class);
    }

    @PutMapping("/modifyUser/{id}")
    public ResponseEntity<String> modifyUser(@PathVariable Integer id, @RequestBody User dto, HttpSession session) {
        if (!isAdmin(session))
            return ResponseEntity.status(403).body("Accès refusé");

        HttpEntity<User> requestEntity = new HttpEntity<>(dto);
        return restTemplate.exchange(clientBaseUrl + "/modifyUser/" + id, HttpMethod.PUT, requestEntity, String.class);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id, HttpSession session) {
        if (!isAdmin(session))
            return ResponseEntity.status(403).body("Accès refusé");
        restTemplate.delete(clientBaseUrl + "/deleteUser/" + id);
        return ResponseEntity.ok("Utilisateur supprimé");
    }

    @GetMapping("/getEntreprises")
    public ResponseEntity<Entreprise[]> getAllEntreprises() {
        return restTemplate.getForEntity(adminBaseUrl + "/getEntreprises", Entreprise[].class);
    }

    @GetMapping("/getEntreprise/{id}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable Integer id) {
        return restTemplate.getForEntity(adminBaseUrl + "/getEntreprises/" + id, Entreprise.class);
    }
}
