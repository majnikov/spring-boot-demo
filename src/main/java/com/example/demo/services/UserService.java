package com.example.demo.services;

import com.example.demo.models.User;

import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
public interface UserService {

    List<User> getAll();

    User getById(Integer id);

    List<User> getByName(String name);

    void removeById(Integer id);

    User updateNameById(Integer id, String name);

    User createUserByName(String name);

}
