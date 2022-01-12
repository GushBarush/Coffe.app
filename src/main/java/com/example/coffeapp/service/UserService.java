package com.example.coffeapp.service;

import com.example.coffeapp.dto.user.UserDTO;
import com.example.coffeapp.entity.user.Role;
import com.example.coffeapp.entity.user.User;
import com.example.coffeapp.repository.RoleRepo;
import com.example.coffeapp.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByPhoneNumber(s);
    }

    public List<UserDTO> allUser() {
        List<User> entity = userRepo.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (User user : entity) {
            userDTOS.add(mapper.map(user, UserDTO.class));
        }
        return userDTOS;
    }

    public List<UserDTO> userByNumber(String number){
        List<User> entity = userRepo.findByUserNumber(number);
        List<UserDTO> userDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (User user : entity) {
            userDTOS.add(mapper.map(user, UserDTO.class));
        }
        return userDTOS;
    }

    public boolean haveUser(UserDTO userDTO){
        User userInDB = userRepo.findByPhoneNumber(userDTO.getPhoneNumber());

        return userInDB != null;
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
        user.setUserNumber(user.getPhoneNumber().substring(user.getPhoneNumber().length() - 4));
    }

    public void addUser(UserDTO userDTO) {

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setCoffee(0);
        userDTO.setHappyCoffee(0);
        userDTO.setActive(true);

        ModelMapper mapper = new ModelMapper();

        User userEntity = mapper.map(userDTO, User.class);

        setNewUserNumber(userEntity);
        userEntity.setRoles(Collections.singletonList(roleRepo.findByRoleName("USER")));

        userRepo.save(userEntity);
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
        user.setName(newName);

        List<String> roles = new ArrayList<>();

        for (Role role : roleRepo.findAll()) {
            roles.add(role.getRoleName());
        }

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(roleRepo.findByRoleName(key));
            }
        }

        saveUser(user);
    }
}
