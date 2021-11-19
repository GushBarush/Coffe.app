package com.example.coffeapp.repository;

import com.example.coffeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String userName);
    User findByEmail(String email);
    User findByNumberPhone(Long numberPhone);

}
