package org.example.cinemapjt.config;


import org.example.cinemapjt.service.GradeService;
import org.example.cinemapjt.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.*;
import org.example.cinemapjt.domain.repository.*;

// Config의 Configuration, @Bean을 통해서 DI 받을 빈을 등록시킴
@Configuration
public class AppConfig {

    private final DiscountRepository discountRepository;

    public AppConfig(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService();
    }

    @Bean
    public GradeService gradeService() {
        return new GradeService(discountRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
