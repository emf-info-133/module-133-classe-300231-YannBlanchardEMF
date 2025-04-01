package client.servicerestclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 🔓 Désactive toute forme de sécurité (aucune auth requise)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // désactive CSRF pour éviter les erreurs avec POST depuis Postman
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // autorise tout
        return http.build();
    }

    // 🔐 Fournit un encodeur de mots de passe que tu peux appeler depuis WrkDB
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
