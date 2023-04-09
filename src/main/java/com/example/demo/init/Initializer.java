package com.example.demo.init;

import com.example.demo.model.UserModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Initializer implements CommandLineRunner {

    UserService userService;

    PasswordEncoder passwordEncoder;

    @Autowired
    public Initializer(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {

        if (userService.findByEmail("admin@admin.com") == null){
            UserModel userModel = new UserModel();
            userModel.setName("Admin");
            userModel.setEmail("admin@admin.com");
            userModel.setPassword(passwordEncoder.encode("nimda"));
            userModel.setRoles("USER_ADMIN");

            userService.creer(userModel);
        }
        if (userService.findByEmail("super@admin.com") == null){
            UserModel userModel = new UserModel();
            userModel.setName("Super Admin");
            userModel.setEmail("super@admin.com");
            userModel.setPassword(passwordEncoder.encode("nimda"));
            userModel.setRoles("USER_SUPER_ADMIN");

            userService.creer(userModel);
        }
    }

}

