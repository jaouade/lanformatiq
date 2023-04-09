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
public interface UserRepository extends CrudRepository<UserModel, Long> {

    UserModel findByEmail(String email);
    List<UserModel> findAllByRolesContainingAndEmailNot(String s, String email);
}
