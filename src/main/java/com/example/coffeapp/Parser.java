package com.example.coffeapp;

import com.example.coffeapp.entity.Role;
import com.example.coffeapp.entity.User;
import com.example.coffeapp.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.*;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/parser")
@PreAuthorize("hasAuthority('ADMIN')")
public class Parser {

    final UserRepo userRepo;

    public Parser(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String parser() {
        try {
            File file = new File("/Coffe.app/src/main/resources/base.txt");

            FileReader fr = new FileReader(file);

            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                User user = new User();

                String[] lineArray = line.split(";");

                String numberPhone = lineArray[0];

                if (numberPhone.length() != 11) {
                    continue;
                }

                String name =lineArray[1];

                ThreadLocalRandom random = ThreadLocalRandom.current();

                Long a = random.nextLong(10000, 99999);

                user.setUsername(numberPhone.substring(1));
                user.setNameUser(name);
                user.setPassword(String.valueOf(a));
                user.setNewUserNumber();
                user.setCoffee(0);
                user.setHappyCoffee(0);
                user.setActive(true);
                user.setRoles(Collections.singleton(Role.USER));
                userRepo.save(user);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:";
        }
        return "userList";
    }
}