package com.library.demo.repository;

import com.library.demo.domain.Enums.UserRole;
import com.library.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(UserRole role);
}
