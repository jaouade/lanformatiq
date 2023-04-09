package com.example.demo.services;

import com.example.demo.model.TodoModel;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.TodoRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserModel creer(UserModel userModel){
        return userRepository.save(userModel);
    }
    public UserModel modifier(UserModel userModel){
        return userRepository.save(userModel);
    }

    @PreAuthorize("hasRole('USER_SUPER_ADMIN')")
    public void supprimer(UserModel userModel){
        userRepository.delete(userModel);
    }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
    public UserModel findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserModel getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserModel user = this.findByEmail(currentPrincipalName);
        return user;
    }

    public List<UserModel> getAllUsers(){
        return (List<UserModel>) userRepository.findAll();
    }

    public UserModel getById(Long id){
        return userRepository.findById(id).get();
    }

    public List<UserModel> findAllByRolesContainingAndEmailNot(String role, String email){
        return userRepository.findAllByRolesContainingAndEmailNot(role, email);
    }


}
