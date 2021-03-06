package com.example.coffeapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO implements Serializable {

    private Long id;

    private String phoneNumber;

    private String password;

    private String name;

    private String userNumber;

    private int coffee;

    private int happyCoffee;

    private boolean active;

    private List<RoleDTO> roles;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", coffee=" + coffee +
                ", happyCoffee=" + happyCoffee +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
