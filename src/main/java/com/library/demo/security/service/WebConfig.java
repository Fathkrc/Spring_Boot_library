package com.library.demo.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity//Web security sistemimizi aktif hale getiriyor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//methodları hangi rollerin kullanabileceğini set edebilmek için
public class WebConfig  extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;



    public WebConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests().
                antMatchers(HttpMethod.GET,"/library/**").hasRole("ROLE_ADMIN").
                antMatchers(HttpMethod.DELETE,"/library/**").hasRole("ROLE_ADMIN").
                antMatchers(HttpMethod.PUT,"/library/**").hasRole("ROLE_ADMIN")
                .and().authorizeHttpRequests()
                .antMatchers( "index.html", "/css/*", "/js/*", "/register","/login").permitAll()
                .and().httpBasic();

        /*
        http.csrf().disable().
                authorizeHttpRequests().
                antMatchers("/","index.html","/css/*","/js/*").permitAll().
                anyRequest().
                authenticated().
                and().
                httpBasic();
        getpointimizi csrf disable ile güvenli hale getirdik ve crud operasyonlarımızın yetkiye göre
        girişi sınırlandırdık permit all ile register ve belli endpoinlere tüm kullanıcıların ulaşabilmesini sağladık

         */
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);

    }
}