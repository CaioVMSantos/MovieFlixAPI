package br.com.movieflix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 1. Desabilita o CSRF (Cross-Site Request Forgery), comum para APIs stateless.
                .csrf(csrf -> csrf.disable())

                // 2. Configura a gestão de sessão como STATELESS.
                // A API não guardará estado de sessão no servidor.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Configura as regras de autorização para os requests HTTP.
                .authorizeHttpRequests(authorize -> authorize
                        // Permite que qualquer um faça uma requisição POST para "/users/register"
                        // **IMPORTANTE:** Altere "/users/register" para a rota exata do seu controller de registro.
                        .requestMatchers(HttpMethod.POST, "/users/register").permitAll()

                        // 4. Para todas as outras requisições, exige autenticação.
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorna a implementação do BCrypt para codificar senhas.
        return new BCryptPasswordEncoder();
    }
}