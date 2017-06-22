package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

}
