package com.example.coffeapp.service;

import com.example.coffeapp.entity.Role;
import com.example.coffeapp.entity.User;
import com.example.coffeapp.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public Iterable<User> allUser() {
        return userRepo.findAll();
    }

    public Iterable<User> userByNumber(String number){
        return userRepo.findByUserNumber(number);
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

    public Iterable<User> userFilterNumber(String filter) {

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
