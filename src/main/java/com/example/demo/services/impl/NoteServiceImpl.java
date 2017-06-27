package com.example.demo.services.impl;

import com.example.demo.models.Note;
import com.example.demo.models.User;
import com.example.demo.repositories.NoteRepository;
import com.example.demo.services.NoteService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private UserService userService;

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
        User user = userService.findOne(id);
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
        User user = userService.findOne(id);
        if (null == user) {
            throw new EntityNotFoundException(String.format("There is no user with such id: %d", id));
        }

        return noteRepository.findByUserId(id);
    }

    @Override
    public void removeNoteById(Integer userId, Integer noteId) {
        Note note = noteRepository.getOne(noteId);
        if (null == note) {
            throw new EntityNotFoundException(String.format("There is no note with such id: %d", noteId));
        }

        if (!note.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException(String.format("Note doesn't belong to the user with id: %d", userId));
        }

        noteRepository.delete(note);
    }

    @Override
    @Transactional
    public void removeAllUserNotes(Integer id) {
        User user = userService.findOne(id);
        if (null == user) {
            throw new EntityNotFoundException(String.format("There is no user with such id: %d", id));
        }

        List<Note> notes = noteRepository.findByUserId(id);
        noteRepository.delete(notes);
    }

    @Override
    public Note updateNoteById(Integer userId, Integer noteId, String noteText) {
        Note note = noteRepository.getOne(noteId);
        if (null == note) {
            throw new EntityNotFoundException(String.format("There is no note with such id: %d", noteId));
        }

        if (!note.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException(String.format("Note doesn't belong to the user with id: %d", userId));
        }

        note.setText(noteText);
        return noteRepository.saveAndFlush(note);
    }


}
