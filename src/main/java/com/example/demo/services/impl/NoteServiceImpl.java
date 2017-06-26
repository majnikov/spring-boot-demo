package com.example.demo.services.impl;

import com.example.demo.models.Note;
import com.example.demo.models.User;
import com.example.demo.repositories.NoteRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note getById(Integer id) {
        return noteRepository.findOne(id);
    }

    @Override
    public Note addNoteToUserById(Integer id, String text) {

        User user = userRepository.findOne(id);

        if (null == user) {
            throw new EntityNotFoundException(String.format("There is no user with such id: %d", id));
        }

        Note newNote = new Note();

        newNote.setText(text);
        newNote.setUser(user);

        return noteRepository.saveAndFlush(newNote);
    }

    @Override
    public List<Note> getAllUserNotesById(Integer id) {

        User user = userRepository.findOne(id);

        if (null == user) {
            throw new EntityNotFoundException(String.format("There is no user with such id: %d", id));
        }

        return noteRepository.findByUserId(id);
    }

    @Override
    public void removeNoteById(Integer noteId) {
        Note note = noteRepository.getOne(noteId);

        if (null == note) {
            throw new EntityNotFoundException(String.format("There is no note with such id: %d", noteId));
        }

        noteRepository.delete(note);
    }

    @Override
    public void removeAllUserNotes(Integer id) {

        User user = userRepository.findOne(id);

        if (null == user) {
            throw new EntityNotFoundException(String.format("There is no user with such id: %d", id));
        }

        List<Note> notes = noteRepository.findByUserId(id);

        noteRepository.delete(notes);
    }


}
