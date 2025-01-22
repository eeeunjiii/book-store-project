package com.spring.project.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests((authorizeRequest) ->
                    authorizeRequest.requestMatchers("/cart/**", "/profile/**", "/order/**").authenticated()
                            .requestMatchers("/manager/**").hasAuthority("ADMIN")
                            .anyRequest().permitAll()
                )
                .formLogin((formLoginConfigurer) ->
                    formLoginConfigurer.usernameParameter("email")
                            .passwordParameter("password")
                            .loginPage("/login")
                            .successHandler(new LoginSuccessHandler("/"))
                            .permitAll()
                )
                .rememberMe((httpSecurityRememberMeConfigurer) ->
                    httpSecurityRememberMeConfigurer.key("security")
                        .rememberMeParameter("remember-me")
                        .tokenValiditySeconds(60*60*24*10)
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

    public static class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
        public LoginSuccessHandler(String defaultTargetUrl) {
            setDefaultTargetUrl(defaultTargetUrl);
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            HttpSession session= request.getSession();
            SavedRequest savedRequest=new HttpSessionRequestCache().getRequest(request, response);

            if(savedRequest!=null) {
                String targetUrl=savedRequest.getRedirectUrl();
                getRedirectStrategy().sendRedirect(request, response, targetUrl);
            } else if(session!=null) {
                String redirectUrl=(String) session.getAttribute("prevPage");
                if(redirectUrl!=null) {
                    session.removeAttribute("prevPage");
                    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
                } else {
                    super.onAuthenticationSuccess(request, response, authentication);
                }
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }
    }
}
