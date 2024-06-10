package com.twitter.mini.security.config;

import com.twitter.mini.repository.UserRepository;
import com.twitter.mini.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/auth/**").permitAll();
//                    auth.anyRequest().permitAll();
                    auth.anyRequest().authenticated();
                });

        httpSecurity
                .authenticationProvider(authenticationProvider());

        //jwt filter

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        //cors orgin
//        httpSecurity
//                .cors(cors->cors.configurationSource(configuration()));



        return httpSecurity.build();
    }

//    @Bean
//    public CorsConfigurationSource configuration() {
//        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowedOrigins(Arrays.asList("*")); // Replace with your exact frontend origin
//        config.setAllowedHeaders(List.of("*"));
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow common HTTP methods
//        config.setAllowedHeaders(List.of("Content-Type", "Authorization", "X-Requested-With")); // Allow necessary headers
////        config.setAllowCredentials(true); // Allow sending cookies if needed (be cautious)
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config); // Apply CORS configuration to all paths
//        return source;
//
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=
                new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
