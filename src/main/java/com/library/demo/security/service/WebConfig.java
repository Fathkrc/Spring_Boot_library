package com.library.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity//Web security sistemimizi aktif hale getiriyor
@EnableGlobalMethodSecurity(prePostEnabled = true)//methodları hangi rollerin kullanabileceğini set edebilmek için
public class WebConfig extends WebSecurityConfiguration {
    @Autowired
    UserDetailService userDetailService;


    protected void WebConfig(HttpSecurity http) throws Exception {
       // http.csrf().disable().


    }
}
