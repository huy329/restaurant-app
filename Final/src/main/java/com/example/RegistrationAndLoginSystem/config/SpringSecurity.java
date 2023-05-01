package com.example.RegistrationAndLoginSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**", "/css/**", "/js/**", "/images/**").permitAll()
                                .requestMatchers(
                                                "/", 
                                                "/index",
                                                "/user",  
                                                "/userCreate",
                                                "/userCreateForm", 
                                                "/userUpdateForm", 
                                                "/depositForm", 
                                                "/depositProcess", 
                                                "/booking", 
                                                "bookingForm", 
                                                "/bookingProcess", 
                                                "/reviewForm", 
                                                "/reviewProcess", 
                                                "/reviewFood",
                                                "/bill",
                                                "/billProcess",
                                                "/deposit",
                                                "/review",
                                                "/table",
                                                "/tableCreate",
                                                "/tableCreateForm", 
                                                "/tableUpdateForm",
                                                "/tableUpdate",
                                                "/tableDelete",
                                                "/food",
                                                "/foodCreate",
                                                "/foodCreateForm",
                                                "/foodUpdateForm",
                                                "/foodUpdate",
                                                "/foodDelete",
                                                "/foodStage", 
                                                "/foodStageProcess", 
                                                "/billStatusProcess",
                                                "/customerUpdateForm",
                                                "/foodMenu",
                                                "/foodMenuDetail",
                                                "/home",
                                                "/customerUpdateForm",
                                                "/customerUpdate",
                                                "/history",
                                                "/userStatusStop").hasAnyRole("ADMIN", "USER", "CHEF")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}