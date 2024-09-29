package com.spring.project.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests((authorizeRequest) ->
                    authorizeRequest.requestMatchers("/cart/**", "/profile/**", "/order/**", "/manager/**").authenticated()
//                            .requestMatchers("/manager/**").hasAnyRole("ADMIN")
                            .anyRequest().permitAll()
                )
                .formLogin((formLoginConfigurer) ->
                    formLoginConfigurer.usernameParameter("email")
                            .passwordParameter("password")
                            .loginPage("/login")
                            .defaultSuccessUrl("/")
                            .failureUrl("/login")
                )
                .logout((logout) ->
                        logout
                                .logoutUrl("/logout")
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .logoutSuccessUrl("/")
                );
        return http.build();
    }
}
