package com.example.demo.controllers;


import com.example.demo.model.UserModel;
import com.example.demo.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class IndexController {


    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public IndexController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @GetMapping(value = "/creer-compte")
    public String creerCompte(Model model) {
        model.addAttribute("user", new UserModel());
        return "creer-compte";
    }

    @PostMapping(value = "/creer-compte")
    public String creerComptePost(@ModelAttribute UserModel userModel, RedirectAttributes redirectAttributes) {

        if (ObjectUtils.isEmpty(userModel.getPassword()) || userModel.getPassword().length() < 4) {
            redirectAttributes.addFlashAttribute("passwordShort", "Mot de passe court, veuillez en fournir un de plus de 4 caractères");
            return "redirect:/creer-compte";
        }

        if (userService.findByEmail(userModel.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("emailAlreadyExists", "L'utilisateur existe déjà");
            return "redirect:/creer-compte";
        }

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles("USER_3ADI");
        userModel.setDisabled(true);
        userService.creer(userModel);
        redirectAttributes.addFlashAttribute("successAccountCreation", "Vous avez créé votre compte avec succès, veuillez vous connecter");
        return "redirect:/se-connecter";
    }
}
