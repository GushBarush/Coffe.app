package com.example.coffeapp.dto.user;

import com.example.coffeapp.entity.User;

public class UserMapper {

    public UserDTO map(User user) {
        UserDTO userDTO = new UserDTO(
                String.valueOf(user.getId()),
                user.getUsername(),
                user.getNameUser(),
                user.getUserNumber(),
                user.getCoffee(),
                user.getHappyCoffee(),
                user.getRoles());
        return userDTO;

    }
}
