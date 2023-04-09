package com.example.demo.services;

import com.example.demo.model.TodoModel;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;


    public TodoModel creer(TodoModel todoModel){
        return todoRepository.save(todoModel);
    }

    public TodoModel recupperer(Long id){
       return todoRepository.findById(id).get();
    }

    public List<TodoModel> recuppererTous(){
        return (List<TodoModel>) todoRepository.findAll();
    }

    public void supprimer(Long id){
        todoRepository.deleteById(id);
    }

    public void supprimer(TodoModel todoModel){
        todoRepository.delete(todoModel);
    }
    public void supprimerTous(){
        todoRepository.deleteAll();
    }
    public TodoModel modifier(TodoModel todoModel){
        return todoRepository.save(todoModel);
    }
    public List<TodoModel> recuppererTousPourUtilisateurConnecte(UserModel userModel){
        return todoRepository.findAllByUser(userModel);
    }
    public void supprimerTousPourUtilisateurConnecte(UserModel userModel){
        todoRepository.deleteAllByUser(userModel);
    }

    public TodoModel recuppererParUtilisateurEtTask(UserModel userModel, String task){
        return todoRepository.findByUserAndTask(userModel, task);
    }

}
