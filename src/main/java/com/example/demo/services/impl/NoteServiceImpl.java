package com.example.demo.services.impl;

import com.example.demo.models.Note;
import com.example.demo.models.User;
import com.example.demo.repositories.NoteRepository;
import com.example.demo.services.NoteService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final UserService userService;

    @Autowired
    @Lazy
    public NoteServiceImpl(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

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
    @Transactional
    public Note addNoteToUserWithFileById(Integer id, String text, MultipartFile uploadedFile) {
        User user = userService.findOne(id);

        String destination = "uploads/" + id + "/" + uploadedFile.getOriginalFilename();

        try {
            uploadedFile.transferTo(new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
