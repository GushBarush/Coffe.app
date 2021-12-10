package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.Role;
import com.example.coffeapp.entity.User;
import com.example.coffeapp.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    final UserRepo userRepo;

    Iterable<User> users;

    public AdminUserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String userList(@RequestParam(required = false, defaultValue = "") String filter ,Model model) {

        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findByUserNumber(filter);
        } else {
            users = userRepo.findAll();
        }

        model.addAttribute("users", users);
        model.addAttribute("filter", filter);

        return "userList";
    }
    
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam String nameUser,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        user.setNameUser(nameUser);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/admin";
    }

    @GetMapping("delete/{user}")
    public String userDelete(@PathVariable User user) {
        userRepo.delete(user);

        return "redirect:/admin";
    }
}
