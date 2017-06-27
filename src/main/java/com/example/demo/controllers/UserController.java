package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
    public User getOneUserById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<User> getOneUserByName(@PathVariable String name) {
        return userService.getByName(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<User> getOneUserByNameThrowParam(@RequestParam String name) {
        return userService.getByName(name);
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.DELETE)
    public void removeUserById(@PathVariable Integer id) {
        userService.removeById(id);
    }

    @RequestMapping(value = "/{id:[\\d]+}/{name}", method = RequestMethod.PUT)
    public User updateUserById(@PathVariable Integer id, @PathVariable String name) {
        return userService.updateNameById(id, name);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public User createUserByName(@PathVariable String name) {
        return userService.createUserByName(name);
    }

}
