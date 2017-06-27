package com.example.demo.services;

import com.example.demo.models.Note;

import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
public interface NoteService {

    List<Note> getAll();

    Note getById(Integer noteId);

    Note addNoteToUserById(Integer userId, String text);

    List<Note> getAllUserNotesById(Integer userId);

    void removeNoteById(Integer userId, Integer noteId);

    void removeAllUserNotes(Integer userId);

    Note updateNoteById(Integer userId, Integer noteId, String noteText);
}
