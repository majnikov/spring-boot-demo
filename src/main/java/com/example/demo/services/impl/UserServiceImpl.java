package com.example.demo.services.impl;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void removeById(Integer id) {
        if (null == userRepository.findOne(id)) {
            throw new EntityNotFoundException(String.format("There is no user with such id: %d", id));
        }
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public User updateNameById(Integer id, String name) {

        if (null == userRepository.findOne(id)) {
            throw new EntityNotFoundException(String.format("There is no user with such id: %d", id));
        }

        User user = userRepository.getOne(id);
        user.setName(name);
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User createUserByName(String name) {

        if (!userRepository.findByName(name).isEmpty()) {
            throw new DuplicateKeyException(String.format("There is already user exist with such name: %s", name));
        }

        User user = new User();
        user.setName(name);
        return userRepository.saveAndFlush(user);
    }


}
