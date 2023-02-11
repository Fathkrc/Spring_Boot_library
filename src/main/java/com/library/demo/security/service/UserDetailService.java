package com.library.demo.security.service;

import com.library.demo.domain.User;
import com.library.demo.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailService implements UserDetailsService {

//User ımızı bu classta Springin istediği tarza  yani UserDetailService olarak tanıtıp Springe okunur duruma getirdik
    private final UserRepository userRepo;

    public UserDetailService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       User user=userRepo.findByUserName(userName).orElseThrow(
               ()->  new UsernameNotFoundException("username not found "+userName)
       );
       if(user!=null) {
           return new org.springframework.security.core.userdetails.User(user.getUserName(),
                   user.getPassword(),
                   Stream.of(user.getUserRole()).
                           map(t-> new SimpleGrantedAuthority(t.name())).collect(Collectors.toList()));
       }else{
           throw new UsernameNotFoundException("username not found");
       }
    }



}
