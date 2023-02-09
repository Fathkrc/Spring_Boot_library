package com.library.demo.service;

import com.library.demo.DTO.UserRequest;
import com.library.demo.domain.Enums.UserRole;
import com.library.demo.domain.Role;
import com.library.demo.domain.User;
import com.library.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final RoleService roleService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    public UserService(RoleService roleService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
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

        //role setting
        Role role=roleService.getRolebyType(UserRole.ROLE_ADMIN);
        Set<Role> set=new HashSet<>();
        set.add(role);
        user.setRoles(set);
        userRepository.save(user);


    }
}
