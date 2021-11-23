package com.example.coffeapp.repository;

import com.example.coffeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String userName);
    User findByEmail(String email);
    List<User> findByUserNumber(String filter);
}
