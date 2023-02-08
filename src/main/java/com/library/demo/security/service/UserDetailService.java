package com.library.demo.security.service;

import com.library.demo.domain.Role;
import com.library.demo.domain.User;
import com.library.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       User user=userRepo.findByUserName(userName).orElseThrow(
               ()->  new UsernameNotFoundException("username not found")
       );
       if(user!=null) {
           return new org.springframework.security.core.userdetails.User(user.getUserName(),
                   user.getPassword(),
                   setAuthorities(user.getRoles()));
       }else{
           throw new UsernameNotFoundException("username not found");
       }
    }

    private List<SimpleGrantedAuthority> setAuthorities(Set<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role w:roles) {
            list.add(new SimpleGrantedAuthority(w.getName().name()));
        }
 // bu methotta User'ımızın String tipinde olan Role unu Springe tanıtarak GrantedAuthorities e çevirdik
        return list;
    }

}
