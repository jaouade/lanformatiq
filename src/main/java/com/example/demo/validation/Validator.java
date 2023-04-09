package com.example.demo.validation;

import com.example.demo.dto.TodoDTO;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static List<String> validateTodo(TodoDTO todoNew) {
        List<String> errors = new ArrayList<>();
        if (todoNew.getTask().isEmpty()){
            errors.add("Task mymknch tkon khawya");
        }
        if (todoNew.getTask().length()<4){
            errors.add("Task khas ykono ktar mn 4 les characteres");
        }
        return errors;
    }
}
