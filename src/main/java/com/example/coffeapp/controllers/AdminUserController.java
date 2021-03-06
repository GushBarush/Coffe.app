package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.user.UserDTO;
import com.example.coffeapp.entity.user.User;
import com.example.coffeapp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(@RequestParam(required = false, defaultValue = "") String filter ,Model model) {

        model.addAttribute("users", userService.userFilterNumber(filter));
        model.addAttribute("filter", filter);

        return "userList";
    }
    
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){

        UserDTO refreshDTO = userService.getInfo(user);

        model.addAttribute("user", refreshDTO);
        model.addAttribute("roles", userService.getAllRoles());

        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        userService.updateUser(user, name, form);

        return "redirect:/admin";
    }

    @GetMapping("delete/{user}")
    public String userDelete(@PathVariable User user) {
        userService.delUser(user);

        return "redirect:/admin";
    }
}
