package com.tcellpair3.cartservice.core.configuration;

import com.turkcell.tcell.core.security.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CartSecurityConfiguration {
    private final BaseSecurityService baseSecurityService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        baseSecurityService.configureCommonSecurityRules(http);
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers("/api/v1/carts/findAll").authenticated()
                        .requestMatchers("/api/v1/orders/save").permitAll()
                        .anyRequest().permitAll()
        );
        return http.build();
    }
}
