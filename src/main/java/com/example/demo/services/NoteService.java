package com.example.demo.services;

import com.example.demo.models.Note;

import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
public interface NoteService {

    List<Note> getAll();

    Note getById(Integer id);

    Note addNoteToUserById(Integer id, String text);

    List<Note> getAllUserNotesById(Integer id);

    void removeAllUserNotes(Integer id);

}
