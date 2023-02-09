package com.library.demo.service;

import com.library.demo.domain.Enums.UserRole;
import com.library.demo.domain.Role;
import com.library.demo.exceptions.ResourceNotFoundExc;
import com.library.demo.repository.RoleRepo;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepo roleRepository;

    public RoleService(RoleRepo roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role getRolebyType(UserRole roleAdmin) {
     return   roleRepository.findByName(roleAdmin).orElseThrow(
                ()-> new ResourceNotFoundExc("Role bulunamadı..")
        );

    }
}
