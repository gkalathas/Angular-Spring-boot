package com.tutorialapi.repository;

import com.tutorialapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}