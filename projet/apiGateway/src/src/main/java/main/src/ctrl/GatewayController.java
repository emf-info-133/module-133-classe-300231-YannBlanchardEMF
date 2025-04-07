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
    public ResponseEntity<?> login(@RequestBody ClientDTO dto) {
        return restTemplate.postForEntity(clientBaseUrl + "/login", dto, User.class);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClientDTO dto) {
        return restTemplate.postForEntity(clientBaseUrl + "/register", dto, User.class);
    }

    @PostMapping("/commande")
    public ResponseEntity<?> commander(@RequestBody ClientDTO dto) {
        return restTemplate.postForEntity(clientBaseUrl + "/commande", dto, String.class);
    }

    // ENTREPRISE
    @PostMapping("/addMenu")
    public ResponseEntity<String> addMenu(@RequestParam String nom, @RequestParam Integer prix_unitaire,
            @RequestParam Integer userId) {
        String url = entrepriseBaseUrl + "/addMenu?nom=" + nom + "&prix_unitaire=" + prix_unitaire + "&userId="
                + userId;
        return restTemplate.postForEntity(url, null, String.class);
    }

    @PostMapping("/modifyMenu")
    public ResponseEntity<String> modifyMenu(@RequestParam Integer pk_menu, @RequestParam String nom,
            @RequestParam Integer prix_unitaire, @RequestParam Integer userId) {
        String url = entrepriseBaseUrl + "/modifyMenu?pk_menu=" + pk_menu + "&nom=" + nom +
                "&prix_unitaire=" + prix_unitaire + "&userId=" + userId;
        return restTemplate.postForEntity(url, null, String.class);
    }

    @PostMapping("/deleteMenu")
    public ResponseEntity<String> deleteMenu(@RequestParam Integer pk_menu, @RequestParam Integer userId) {
        String url = entrepriseBaseUrl + "/deleteMenu?pk_menu=" + pk_menu + "&userId=" + userId;
        return restTemplate.postForEntity(url, null, String.class);
    }

    @GetMapping("/getMenuList")
    public ResponseEntity<Object> getMenuList() {
        String url = entrepriseBaseUrl + "/getMenu";
        return restTemplate.getForEntity(url, Object.class);
    }

    // -------------------------------------- ADMIN -------------------------------------- //

    @PostMapping("/addEntreprise")
    public ResponseEntity<?> addEntreprise(@RequestBody EntrepriseDTO dto) {
        return restTemplate.postForEntity(adminBaseUrl + "/addEntreprise", dto, String.class);
    }

    @GetMapping("/getEntreprises")
    public ResponseEntity<?> getAllEntreprises() {
        return restTemplate.getForEntity(adminBaseUrl + "/getEntreprises", Object.class);
    }

    @GetMapping("/getEntreprise/{id}")
    public ResponseEntity<?> getEntrepriseById(@PathVariable Integer id) {
        return restTemplate.getForEntity(adminBaseUrl + "/getEntreprises/" + id, Object.class);
    }

    @PutMapping("/modifyEntreprise/{id}")
    public ResponseEntity<?> modifyEntreprise(@PathVariable Integer id, @RequestBody EntrepriseDTO dto) {
        HttpEntity<EntrepriseDTO> requestEntity = new HttpEntity<>(dto);
        return restTemplate.exchange(adminBaseUrl + "/modifyEntreprise/" + id, HttpMethod.PUT, requestEntity,
                String.class);
    }

    @DeleteMapping("/deleteEntreprise/{id}")
    public ResponseEntity<?> deleteEntreprise(@PathVariable Integer id) {
        restTemplate.delete(adminBaseUrl + "/deleteEntreprise/" + id);
        return ResponseEntity.ok("Entreprise supprimée");
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody AdminDTO dto) {
        return restTemplate.postForEntity(adminBaseUrl + "/addUser", dto, String.class);
    }

    @PutMapping("/modifyUser/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable Integer id, @RequestBody AdminDTO dto) {
        HttpEntity<AdminDTO> requestEntity = new HttpEntity<>(dto);
        return restTemplate.exchange(adminBaseUrl + "/modifyUser/" + id, HttpMethod.PUT, requestEntity, String.class);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        restTemplate.delete(adminBaseUrl + "/deleteUser/" + id);
        return ResponseEntity.ok("Utilisateur supprimé");
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        return restTemplate.getForEntity(adminBaseUrl + "/getUsers", Object.class);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody AdminDTO dto) {
        return restTemplate.postForEntity(adminBaseUrl + "/loginUser", dto, Boolean.class);
    }

}
