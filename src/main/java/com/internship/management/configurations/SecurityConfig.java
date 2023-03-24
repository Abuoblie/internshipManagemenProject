package com.internship.management.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
//here we create the security access filter chain
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //first disable crsf
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**","/api/internships/**").permitAll()//all the path in this list are white listed
                .requestMatchers("/api/enterprise/**").hasAuthority("ent")
                .requestMatchers("/api/intern/**").hasAuthority("intern")
                .anyRequest()//any other path requested
                .authenticated()//will need  authentication
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // we create a stateless session manager to ascertain that every request go true the security check or authenticated
                .and()
                .authenticationProvider(authenticationProvider)// we indicate the dao auth provider
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);//we add this before the to ensure access to the white list will not be hindered
        return http.build();
    }
}
