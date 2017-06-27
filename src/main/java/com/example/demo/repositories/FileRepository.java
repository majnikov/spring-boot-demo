package com.example.demo.repositories;

import com.example.demo.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by maksim on 6/22/17.
 */
public interface FileRepository extends JpaRepository<File, Integer> {
}
