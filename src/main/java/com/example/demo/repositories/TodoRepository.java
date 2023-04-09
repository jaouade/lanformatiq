package com.example.demo.repositories;

import com.example.demo.model.TodoModel;
import com.example.demo.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Crud
// C : create
// R : retrieve
// U : update
// D : delete

@Repository
public interface TodoRepository extends CrudRepository<TodoModel, Long> {

    List<TodoModel> findAllByUser(UserModel user);
    void deleteAllByUser(UserModel userModel);

    TodoModel findByUserAndTask(UserModel user, String task);

}
