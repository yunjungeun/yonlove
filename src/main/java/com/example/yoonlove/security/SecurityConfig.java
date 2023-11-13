package com.example.yoonlove.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //비번 인코더로 사용할 빈
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
             http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                .requestMatchers("/login","/signupview","/signup","/index","/static/**").permitAll()
                .requestMatchers("admin/**").hasAuthority("admin")
                .requestMatchers("admin/**").hasAuthority("system")
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .failureUrl("/login?error")
                        .usernameParameter("user_id")
                        .passwordParameter("pw")
                        .defaultSuccessUrl("/index")
                )
                .logout(logout -> logout
                        .logoutUrl("/login/logout")
                        .logoutSuccessUrl("/index")
                        .invalidateHttpSession(true)
                );
             return http.build();
    }
}
