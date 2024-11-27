package org.example.cinemapjt.config;


import org.example.cinemapjt.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
