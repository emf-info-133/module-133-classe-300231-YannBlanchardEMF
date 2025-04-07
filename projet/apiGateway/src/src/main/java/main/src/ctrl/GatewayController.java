package main.src.ctrl;

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
    public ResponseEntity<String> addMenu(@RequestParam String nom, @RequestParam Integer prix_unitaire, @RequestParam Integer userId) {
        String url = entrepriseBaseUrl + "/addMenu?nom=" + nom + "&prix_unitaire=" + prix_unitaire + "&userId=" + userId;
        return restTemplate.postForEntity(url, null, String.class);
    }

    @PostMapping("/modifyMenu")
    public ResponseEntity<String> modifyMenu(@RequestParam Integer pk_menu, @RequestParam String nom, @RequestParam Integer prix_unitaire, @RequestParam Integer userId) {
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

    // ADMIN


}

