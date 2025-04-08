package main.src.ctrl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import main.src.beans.Entreprise;
import main.src.beans.User;
import main.src.dto.AdminDTO;
import main.src.dto.ClientDTO;
import main.src.dto.EntrepriseDTO;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String clientBaseUrl = "http://localhost:8081";
    private final String entrepriseBaseUrl = "http://localhost:8082";
    private final String adminBaseUrl = "http://localhost:8083";

    // CLIENT
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody ClientDTO dto) {
        return restTemplate.postForEntity(clientBaseUrl + "/login", dto, User.class);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody ClientDTO dto) {
        return restTemplate.postForEntity(clientBaseUrl + "/register", dto, User.class);
    }

    @PostMapping("/commande")
    public ResponseEntity<String> commander(@RequestBody ClientDTO dto) {
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

    // ENTREPRISE
    @PostMapping("/addMenu")
    public ResponseEntity<String> addMenu(@RequestBody EntrepriseDTO dto) {
        return restTemplate.postForEntity(entrepriseBaseUrl + "/addMenu", dto, String.class);
    }

    @PutMapping("/modifyMenu/{id}")
    public ResponseEntity<String> modifyMenu(@PathVariable int pk_menu, @RequestBody EntrepriseDTO dto) {
        HttpEntity<EntrepriseDTO> requestEntity = new HttpEntity<>(dto);
        return restTemplate.exchange(entrepriseBaseUrl + "/modifyMenu/" + pk_menu, HttpMethod.PUT, requestEntity,
                String.class);
    }

    @DeleteMapping("/deleteMenu/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable int pk_menu, @RequestParam int fkEntreprise) {
        // On garde userId en query param si besoin (tu peux le passer aussi dans le
        // body sinon)
        String url = entrepriseBaseUrl + "/deleteMenu/" + pk_menu + "?fkEntreprise=" + fkEntreprise;
        restTemplate.delete(url);
        return ResponseEntity.ok("Menu supprimé");
    }

    @GetMapping("/getMenuList/{fkEntreprise}")
    public ResponseEntity<EntrepriseDTO[]> getMenuList(@PathVariable Integer fkEntreprise) {
        String url = entrepriseBaseUrl + "/getMenu?fk_entreprise=" + fkEntreprise;
        return restTemplate.getForEntity(url, EntrepriseDTO[].class);
    }
    

    // ADMIN

    @PostMapping("/addEntreprise")
    public ResponseEntity<String> addEntreprise(@RequestBody Entreprise dto) {
        return restTemplate.postForEntity(adminBaseUrl + "/addEntreprise", dto, String.class);
    }

    @GetMapping("/getEntreprises")
    public ResponseEntity<Entreprise[]> getAllEntreprises() {
        return restTemplate.getForEntity(adminBaseUrl + "/getEntreprises", Entreprise[].class);
    }

    @GetMapping("/getEntreprise/{id}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable Integer id) {
        return restTemplate.getForEntity(adminBaseUrl + "/getEntreprises/" + id, Entreprise.class);
    }

    @PutMapping("/modifyEntreprise/{id}")
    public ResponseEntity<String> modifyEntreprise(@PathVariable Integer id, @RequestBody Entreprise dto) {
        HttpEntity<Entreprise> requestEntity = new HttpEntity<>(dto);
        return restTemplate.exchange(adminBaseUrl + "/modifyEntreprise/" + id, HttpMethod.PUT, requestEntity,
                String.class);
    }

    @DeleteMapping("/deleteEntreprise/{id}")
    public ResponseEntity<String> deleteEntreprise(@PathVariable Integer id) {
        restTemplate.delete(adminBaseUrl + "/deleteEntreprise/" + id);
        return ResponseEntity.ok("Entreprise supprimée");
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User dto) {
        return restTemplate.postForEntity(adminBaseUrl + "/addUser", dto, String.class);
    }

    @PutMapping("/modifyUser/{id}")
    public ResponseEntity<String> modifyUser(@PathVariable Integer id, @RequestBody User dto) {
        HttpEntity<User> requestEntity = new HttpEntity<>(dto);
        return restTemplate.exchange(adminBaseUrl + "/modifyUser/" + id, HttpMethod.PUT, requestEntity, String.class);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        restTemplate.delete(adminBaseUrl + "/deleteUser/" + id);
        return ResponseEntity.ok("Utilisateur supprimé");
    }

    @GetMapping("/getUsers")
    public ResponseEntity<User[]> getUsers() {
        return restTemplate.getForEntity(adminBaseUrl + "/getUsers", User[].class);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<Boolean> loginUser(@RequestBody User dto) {
        return restTemplate.postForEntity(adminBaseUrl + "/loginUser", dto, Boolean.class);
    }

}
