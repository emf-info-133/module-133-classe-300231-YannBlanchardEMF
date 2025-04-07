package main.src.ctrl;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String clientBaseUrl = "http://localhost:8081";
    private final String entrepriseBaseUrl = "http://localhost:8082";
    private final String adminBaseUrl = "http://localhost:8083";

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

    @PostMapping("/menu")
    public ResponseEntity<?> ajouterMenu(@RequestBody EntrepriseDTO dto) {
        return restTemplate.postForEntity(entrepriseBaseUrl + "/menu", dto, String.class);
    }

    @PostMapping("/entreprise")
    public ResponseEntity<?> ajouterEntreprise(@RequestBody AdminDTO dto) {
        return restTemplate.postForEntity(adminBaseUrl + "/entreprise", dto, String.class);
    }
}

