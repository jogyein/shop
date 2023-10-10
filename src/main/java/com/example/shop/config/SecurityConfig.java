package com.example.shop.config;

import com.example.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request)-> request

                        .requestMatchers("/","/main", "/members/**", "/item/**", "/assets/**", "/h2-console/**", "/images/**").permitAll()
                        .requestMatchers( "/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())

                .formLogin((form) -> form
                        //.loginPage("/members/login")
                        //.loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/main", true))
                .logout(out -> out
                        .logoutSuccessUrl("/main")
                        .logoutUrl("/members/logout"))
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        // 비밀번호를 데이터베이스에 그대로 저장했을경우, 해킹당하면 고객회원정보가 그대로 노출됨
        // BC어쩌고 해시함수를 이용하여 비밀번호를 암호화하여 저장한다.
    }

}