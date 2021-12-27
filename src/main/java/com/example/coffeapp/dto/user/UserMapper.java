package com.example.coffeapp.dto.user;

import com.example.coffeapp.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDTO map(User user) {
        UserDTO userDTO = new UserDTO(
                String.valueOf(user.getId()),
                user.getPhoneNumber(),
                user.getName(),
                user.getUserNumber(),
                user.getCoffee(),
                user.getHappyCoffee(),
                user.getRoles());
        return userDTO;

    }
}
