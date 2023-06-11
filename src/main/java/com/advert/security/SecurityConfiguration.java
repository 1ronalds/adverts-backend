package com.advert.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin/admin-users").authenticated()
                .antMatchers(HttpMethod.DELETE, "/admin/delete/**").authenticated()
                .antMatchers(HttpMethod.POST, "/admin/new").authenticated()
                .antMatchers(HttpMethod.GET, "/advert/view/all").permitAll()
                .antMatchers(HttpMethod.GET, "/advert/view/user/**").authenticated()
                .antMatchers(HttpMethod.GET, "/advert/view/advert/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/advert/delete/**/**").authenticated()
                .antMatchers(HttpMethod.POST, "/advert/new/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/advert/edit/**/**").authenticated()
                .antMatchers(HttpMethod.GET, "/application/mine/**/formine").authenticated()
                .antMatchers(HttpMethod.GET, "/application/mine/**/forothers").authenticated()
                .antMatchers(HttpMethod.POST, "/application/mine/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/application/mine/**/**").authenticated()
                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/user").authenticated()
                .anyRequest().authenticated();
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("${webserver.url}"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
