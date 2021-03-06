package com.example.coffeapp.repository;

import com.example.coffeapp.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByPhoneNumber(String s);

    List<User> findByUserNumber(String s);
}
