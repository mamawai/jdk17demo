package com.example.demo.security.config;

import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //noinspection removal,deprecation
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/v*/registration/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin();

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // PasswordEncoder里面配置了bCryptPasswordEncoder的Bean
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        // AppUserService 实现了 UserDetailsService，所以可以用在这里
        provider.setUserDetailsService(appUserService);
        return provider;
    }

    /*

    GPT老师说不用配置
    遍历了Spring容器后发现daoAuthenticationProvider自动被注入了
    @Autowired
     public void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.authenticationProvider(daoAuthenticationProvider());
     }
    */
}
