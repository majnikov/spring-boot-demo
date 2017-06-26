package com.example.demo.repositories;

import com.example.demo.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByUserId(Integer id);
}
