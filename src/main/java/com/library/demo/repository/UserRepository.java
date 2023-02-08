package com.library.demo.repository;

import com.library.demo.domain.User;
import com.library.demo.exceptions.ResourceNotFoundExc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUserName(String userName) throws ResourceNotFoundExc;
}
