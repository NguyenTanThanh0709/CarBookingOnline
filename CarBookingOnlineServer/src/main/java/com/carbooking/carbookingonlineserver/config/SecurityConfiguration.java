package com.carbooking.carbookingonlineserver.config;



import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    private static final String[] WHITE_LIST_URL = {"/vnpay-payment/**","/submitOrder/**","/api/v1/auth/**","/api/v0/common/**","api/v1/demo/**"
            };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
//                .cors(cors -> cors.disable())
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/api/v2/admin/**").hasAnyAuthority("ADMIN","OWNER")
                                .requestMatchers("/api/v1/owner/**").hasAnyAuthority("OWNER","STAFF")
                                .requestMatchers("/api/v1/staff/**").hasAnyAuthority("STAFF","USER","OWNER","DRIVER")
                                .requestMatchers("/api/v1/driver/**").hasAnyAuthority("DRIVER")
                                .requestMatchers("/api/v1/user/**").hasAnyAuthority("USER","DRIVER","OWNER","STAFF","ADMIN")
                                .requestMatchers("/api/v1/owner/cars/**").hasAnyAuthority("OWNER","STAFF")
                                .requestMatchers("/api/v1/staff/drivertrip/**").hasAnyAuthority("USER","STAFF")
                                .anyRequest().authenticated()
                ).sessionManagement(sesson -> sesson.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                            logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> {
                                    SecurityContextHolder.clearContext();
                                    response.sendRedirect("http://localhost:3000/login");
                                })
                        )
        ;
        return http.build();
    }



}
