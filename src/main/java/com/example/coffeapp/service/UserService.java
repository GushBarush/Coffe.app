package com.example.coffeapp.service;

import com.example.coffeapp.dto.user.UserDTO;
import com.example.coffeapp.dto.user.UserMapper;
import com.example.coffeapp.entity.Role;
import com.example.coffeapp.entity.User;
import com.example.coffeapp.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public List<UserDTO> allUser() {
        List<User> entity = userRepo.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : entity) {
            userDTOS.add(userMapper.map(user));
        }
        return userDTOS;
    }

    public List<UserDTO> userByNumber(String number){
        List<User> entity = userRepo.findByUserNumber(number);
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : entity) {
            userDTOS.add(userMapper.map(user));
        }
        return userDTOS;
    }

    public boolean haveUser(User user){
        User userDBName = (User) loadUserByUsername(user.getUsername());

        return userDBName != null;
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void delUser(User user) {
        userRepo.delete(user);
    }

    public List<UserDTO> userFilterNumber(String filter) {

        if (filter != null && !filter.isEmpty()) {
            return userByNumber(filter);
        } else {
            return allUser();
        }
    }

    public void setNewUserNumber(User user) {
        user.setUserNumber(user.getUsername().substring(user.getUsername().length() - 4));
    }

    public void addUser(User user) {
        setNewUserNumber(user);
        user.setCoffee(0);
        user.setHappyCoffee(0);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        saveUser(user);
    }

    public void addCoffe(User user){

        if (user.getCoffee() == 5) {
            user.setCoffee(0);
            user.setHappyCoffee(user.getHappyCoffee() + 1);
        } else {
            user.setCoffee(user.getCoffee() + 1);
        }

        saveUser(user);
    }

    public void delHappyCoffe(User user) {

        if (user.getHappyCoffee() != 0) {
            user.setHappyCoffee(user.getHappyCoffee() - 1);
        }

        saveUser(user);
    }

    public void updateUser(User user, String newName, Map<String, String> form) {
        user.setNameUser(newName);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        saveUser(user);
    }
}
