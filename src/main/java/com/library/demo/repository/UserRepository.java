package com.library.demo.repository;

import com.library.demo.domain.User;
import com.library.demo.exceptions.ResourceNotFoundExc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUserName(String userName) throws ResourceNotFoundExc;
}
