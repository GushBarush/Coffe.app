package com.example.coffeapp.repos;

import com.example.coffeapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String userName);
    User findByEmail(String email);
    User findByNumberPhone(Long numberPhone);

}
