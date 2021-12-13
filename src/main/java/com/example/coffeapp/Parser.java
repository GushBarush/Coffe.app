package com.example.coffeapp;

import com.example.coffeapp.service.UserParserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parser")
@PreAuthorize("hasAuthority('ADMIN')")
public class Parser {

    final UserParserService userParserService;

    public Parser(UserParserService userParserService) {
        this.userParserService = userParserService;
    }

    @GetMapping
    public String parser() {

        if (userParserService.parser()) {
            return "userList";
        } else {
            return  "redirect:";
        }
    }
}