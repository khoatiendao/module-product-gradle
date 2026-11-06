package com.example.module_product_gradle.security.config;

import com.example.module_product_gradle.common.CommonEnum.UserRoleEnum;
import com.example.module_product_gradle.security.RestAuthHandlers;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final RestAuthHandlers restAuthHandlers;

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            JwtAuthenticationConverter jwtConverter)
    throws Exception {
        String[] adminManaged = { "/api/product/**", "/api/category/**" };

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(
                        s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/auth/register", "/api/auth/login", "/api/auth/create-admin").permitAll()
                                .requestMatchers(HttpMethod.POST, adminManaged).hasRole((UserRoleEnum.ADMIN).toString())
                                .requestMatchers(HttpMethod.PUT, adminManaged).hasRole((UserRoleEnum.ADMIN).toString())
                                .requestMatchers(HttpMethod.PATCH, adminManaged).hasRole((UserRoleEnum.ADMIN).toString())
                                .requestMatchers(HttpMethod.DELETE, adminManaged).hasRole((UserRoleEnum.ADMIN).toString())
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/category/admin/**").hasRole((UserRoleEnum.ADMIN).toString())
                                .anyRequest().hasAnyRole((UserRoleEnum.USER).toString(), (UserRoleEnum.ADMIN).toString())

                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter))
                        .authenticationEntryPoint(restAuthHandlers)
                        .accessDeniedHandler(restAuthHandlers)
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(restAuthHandlers)
                        .accessDeniedHandler(restAuthHandlers)
                );
        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter authorities = new JwtGrantedAuthoritiesConverter();
        authorities.setAuthoritiesClaimName("role");
        authorities.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authorities);
        return converter;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
