package com.faturamentofrete.frete.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desativa a proteção CSRF para permitir requisições POST, PUT e DELETE no Postman
                .csrf(csrf -> csrf.disable())
                // Libera (permitAll) o acesso a todas as rotas sem precisar de senha
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
}