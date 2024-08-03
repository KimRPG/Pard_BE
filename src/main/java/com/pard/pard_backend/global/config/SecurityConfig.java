package com.pard.pard_backend.global.config;

import com.pard.pard_backend.domain.cookie.service.CookieService;
import com.pard.pard_backend.domain.security.jwt.JWTFilter;
import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.global.responses.errors.handler.AccessDeniedHandlerImpl;
import com.pard.pard_backend.global.responses.errors.handler.JwtAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTUtil jwtUtil;
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final CookieService cookieService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
        roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_OB > ROLE_YB");
        return roleHierarchyImpl;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedOrigin("https://pard-app-project.web.app/");
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfiguration.addAllowedHeader("Authorization");
        corsConfiguration.addAllowedHeader("Origin");
        corsConfiguration.addAllowedHeader("Accept");
        corsConfiguration.addAllowedHeader("X-Requested-With");
        corsConfiguration.addAllowedHeader("X-Content-Type-Options");
        corsConfiguration.addAllowedHeader("X-XSS-Protection");
        corsConfiguration.addAllowedHeader("X-Frame-Options");
        corsConfiguration.addAllowedHeader("Cache-Control");
        corsConfiguration.addAllowedHeader("Content-Type");
        corsConfiguration.addAllowedHeader("Date");
        corsConfiguration.addAllowedHeader("Content-Length");
        corsConfiguration.addAllowedHeader("Keep-Alive");
        corsConfiguration.addAllowedHeader("Connection");
        corsConfiguration.addAllowedHeader("Expires");
        corsConfiguration.addAllowedHeader("Set-Cookie");
        corsConfiguration.addAllowedHeader("Pragma");
        corsConfiguration.addAllowedHeader("Vary");
        corsConfiguration.addAllowedHeader("Access-Control-Request-Method");
        corsConfiguration.addAllowedHeader("Access-Control-Allow-Credentials");
        corsConfiguration.addAllowedHeader("Access-Control-Request-Headers");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L); // preflight 결과를 1시간동안 캐시에 저장
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    //일단 다 때려박음 * 이거 CORS오류 떠서

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf((auth) -> auth.disable());
    http
            .formLogin(auth -> auth.disable());
    http
            .httpBasic(auth -> auth.disable());

    http
            .cors(corsCustomizer -> corsCustomizer.configurationSource(corsConfigurationSource()));

        http
            .addFilterBefore(new JWTFilter(jwtUtil, cookieService), UsernamePasswordAuthenticationFilter.class);
    http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/test","/swagger-ui/**","/v3/api-docs/**","/v1/users/login","/error").permitAll()
                    .requestMatchers(HttpMethod.POST,"/v1/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/v1/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH,"/v1/**").hasRole("ADMIN")
                    .requestMatchers("/v1/**").hasRole("YB")
                    .requestMatchers("/hi/hi").hasRole("OB")
                    .requestMatchers("/hi/hello").hasRole("YB")
                    .anyRequest().authenticated()
            );
    http
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http
            .exceptionHandling(exception -> exception
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler));


    return http.build();
}
}
