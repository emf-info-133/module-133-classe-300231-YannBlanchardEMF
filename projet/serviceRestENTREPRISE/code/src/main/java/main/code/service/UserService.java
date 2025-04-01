package main.code.service;

import main.code.dto.UserResponse;
import main.code.model.User;
import main.code.repository.EntrepriseRepository;
import main.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(UserRepository userRepository,
                       EntrepriseRepository entrepriseRepository,
                       RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.restTemplate = restTemplate;
    }

    public Iterable<UserResponse> findAllUser() {
        Iterable<User> users = userRepository.findAll();
        List<UserResponse> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserResponse userDTO = new UserResponse(
                user.getPkUser(),
                user.getUsername(),
                user.getEntreprise());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}