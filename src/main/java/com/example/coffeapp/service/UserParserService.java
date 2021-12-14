package com.example.coffeapp.service;

import com.example.coffeapp.entity.Role;
import com.example.coffeapp.entity.User;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserParserService {

    private final UserService userService;

    public UserParserService(UserService userService) {
        this.userService = userService;
    }

    public boolean parser() {
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
                    line = reader.readLine();
                    continue;
                }

                String name =lineArray[1];

                ThreadLocalRandom random = ThreadLocalRandom.current();

                Long a = random.nextLong(10000, 99999);

                user.setUsername(numberPhone.substring(1));
                user.setNameUser(name);
                user.setPassword(String.valueOf(a));
                userService.setNewUserNumber(user);
                user.setCoffee(0);
                user.setHappyCoffee(0);
                user.setActive(true);
                user.setRoles(Collections.singleton(Role.USER));
                userService.saveUser(user);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
