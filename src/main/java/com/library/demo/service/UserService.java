package com.library.demo.service;

import com.library.demo.DTO.UserRequest;
import com.library.demo.domain.Enums.UserRole;
import com.library.demo.domain.User;
import com.library.demo.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
  //  private final RoleService roleService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public UserService( UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserRequest userRequest) {
        User user=new User();
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setUserName(userRequest.getUserName());
        String password=userRequest.getPassword();
        String encodedPas=passwordEncoder.encode(password);
        user.setPassword(encodedPas);
        user.setUserRole(UserRole.ROLE_ADMIN);
        //role setting
        userRepository.save(user);


    }
}
