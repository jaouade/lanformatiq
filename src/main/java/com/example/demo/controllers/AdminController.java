package com.example.demo.controllers;

import com.example.demo.model.UserModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Secured({"USER_ADMIN", "USER_SUPER_ADMIN"})
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Secured({"USER_ADMIN", "USER_SUPER_ADMIN"})
    public String admin(Model model){

        List<UserModel> allUsers = userService.findAllByRolesContainingAndEmailNot("USER_3ADI", userService.getCurrentUser().getEmail() );
        model.addAttribute("users", allUsers);
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "administration";
    }

    @GetMapping("/activate/user/{id}")
    @Secured({"USER_ADMIN", "USER_SUPER_ADMIN"})
    public String activate(@PathVariable Long id){
        UserModel userModel = userService.getById(id);
        userModel.setDisabled(false);
        userService.modifier(userModel);
        return "redirect:/admin";
    }

    @GetMapping("/deactivate/user/{id}")
    @Secured({"USER_ADMIN", "USER_SUPER_ADMIN"})
    public String deactivate(@PathVariable Long id){
        UserModel userModel = userService.getById(id);
        userModel.setDisabled(true);
        userService.modifier(userModel);
        return "redirect:/admin";
    }


    @GetMapping("/supprimer/user/{id}")
    @Secured({"USER_SUPER_ADMIN"})
    public String supprimer(@PathVariable Long id){
        UserModel userModel = userService.getById(id);
        userService.supprimer(userModel);
        return "redirect:/admin";
    }

}
