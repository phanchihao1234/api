package com.example.spring_api.config;

import com.example.spring_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
//    @Bean
//    public UserDetailsManager userDetailsManager(){
//        return new InMemoryUserDetailsManager(
//                User.withUsername("hao").password(passwordEncoder().encode("hao")).roles("USER").build()
//        );
//    }
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUsername(username);
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
//                .requestMatchers(HttpMethod.GET,"/api/v1/user/login").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.GET,"/api/v1/user/list/**").hasRole("USER")
//                .requestMatchers(HttpMethod.GET,"/api/v1/category/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/v1/user/list").hasRole("USER")
                .requestMatchers(HttpMethod.POST,"api/v1/user/register").permitAll()
                ///** la tatca trong api/category
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().disable();
        return http.build();
    }
}
