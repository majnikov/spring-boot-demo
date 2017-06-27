package com.example.demo.controllers;

import com.example.demo.models.Note;
import com.example.demo.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Admin on 26.06.2017.
 */
@RestController
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> getAllNotes() {
        return noteService.getAll();
    }

    @RequestMapping(value = "/users/{userId:[\\d]+}/notes", method = RequestMethod.POST)
    public Note addNoteToUserById(@PathVariable Integer userId, @RequestParam String text) {
        return noteService.addNoteToUserById(userId, text);
    }

    @RequestMapping(value = "/users/{userId:[\\d]+}/notes", method = RequestMethod.GET)
    public List<Note> getAllUserNotes(@PathVariable Integer userId) {
        return noteService.getAllUserNotesById(userId);
    }

    @RequestMapping(value = "/users/{userId:[\\d]+}/notes", method = RequestMethod.DELETE)
    public void removeAllUserNotes(@PathVariable Integer userId) {
        noteService.removeAllUserNotes(userId);
    }

    @RequestMapping(value = "/users/{userId:[\\d]+}/notes/{noteId:[\\d]+}", method = RequestMethod.DELETE)
    public void removeNoteById(@PathVariable Integer userId, @PathVariable Integer noteId) {
        noteService.removeNoteById(userId, noteId);
    }

    @RequestMapping(value = "/users/{userId:[\\d]+}/notes/{noteId:[\\d]+}", method = RequestMethod.PUT)
    public Note updateNoteById(@PathVariable Integer userId, @PathVariable Integer noteId, @RequestParam String noteText) {
        return noteService.updateNoteById(userId, noteId, noteText);
    }

}
