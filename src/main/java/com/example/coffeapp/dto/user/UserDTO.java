package com.example.coffeapp.dto.user;

import com.example.coffeapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String username;
    private String nameUser;
    private String userNumber;
    private int coffee;
    private int happyCoffee;
    private Set<Role> roles;

}
