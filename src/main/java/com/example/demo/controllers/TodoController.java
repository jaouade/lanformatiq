package com.example.demo.controllers;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoModel;
import com.example.demo.model.UserModel;
import com.example.demo.services.TodoService;
import com.example.demo.services.UserService;
import com.example.demo.validation.Validator;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    private List<TodoDTO> todoList;

    public TodoController() {
        todoList = new ArrayList<>();
    }

    @GetMapping
    public String todo(Model model) {

        UserModel currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser.getName());
        List<TodoModel> todoModelList = todoService.recuppererTousPourUtilisateurConnecte(userService.getCurrentUser());
        model.addAttribute("todos", todoModelList);
        model.addAttribute("todoDTO", new TodoDTO());
        return "todo";
    }

    // TodoDTO t = new TodoDTO();
    // t.setTask(valeur mn lformulaire)
    // createTodo(t)
    //

    @PostMapping("/create")
    public String createTodo(@ModelAttribute @Valid TodoDTO todoNew, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        UserModel currentUser = userService.getCurrentUser();
        if (bindingResult.hasErrors()) {
            List<TodoModel> todoModelList = todoService.recuppererTousPourUtilisateurConnecte(currentUser);
            model.addAttribute("todos", todoModelList);
            return "todo";
        }
        UUID uuid = Generators.timeBasedGenerator().generate();
        todoNew.setId(uuid.toString());
        this.todoList.add(todoNew);

        TodoModel todoModel = new TodoModel();
        todoModel.setTask(todoNew.getTask());
        todoModel.setFinished(todoNew.isFinished());
        todoModel.setUser(currentUser);

        TodoModel todoFromBaseDeDonnees = todoService.recuppererParUtilisateurEtTask(currentUser, todoNew.getTask());
        if (todoFromBaseDeDonnees != null) {
            redirectAttributes.addFlashAttribute("notUniqueMessage", "Todo deja kayna f lbase de donnees");
        } else {
            todoService.creer(todoModel);
        }
        return "redirect:/todo";
    }

    @PostMapping("/create/manual/validation")
    public String createTodoManualValidation(@ModelAttribute TodoDTO todoNew, Model model) {
        List<String> errors = Validator.validateTodo(todoNew);
        if (!errors.isEmpty()) {
            model.addAttribute("validationErrors", errors);
            return "todo";
        }

        UUID uuid = Generators.timeBasedGenerator().generate();
        todoNew.setId(uuid.toString());
        this.todoList.add(todoNew);
        return "redirect:/todo";
    }

    @GetMapping("/mse7kolchi")
    public String mse7Todos() {
        this.todoList.clear();
        return "redirect:/todo";
    }

    @GetMapping("/mse7Element")
    public String mse7Todo(@RequestParam int index) {
        this.todoList.remove(index);
        return "redirect:/todo";
    }
    // todo/mse7ElementById/1
    // todo/mse7ElementById/2

    @GetMapping("/mse7ElementById/{idTodo}")
    public String mse7TodoById(@PathVariable String idTodo) {
        List<TodoDTO> newTodoList = new ArrayList<>();
        for (TodoDTO todoDTO : todoList) {
            if (!todoDTO.getId().equals(idTodo)) {
                newTodoList.add(todoDTO);
            }
        }
        this.todoList = newTodoList;
        return "redirect:/todo";
    }

    @GetMapping("/mse7MnBaseDonnees/{idTodo}")
    public String mse7MnBaseDonnees(@PathVariable Long idTodo) {
        todoService.supprimer(idTodo);
        return "redirect:/todo";
    }

    @GetMapping("/mse7kolchiMnBaseDonnees")
    public String mse7TodosMnBaseDonnees() {
        todoService.supprimerTousPourUtilisateurConnecte(userService.getCurrentUser());
        return "redirect:/todo";
    }
}
