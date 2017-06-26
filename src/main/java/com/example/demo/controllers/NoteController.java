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

    @RequestMapping(value = "/users/{id:[\\d]+}/notes", method = RequestMethod.POST)
    public Note addNoteToUserById(@PathVariable Integer id, @RequestParam String text) {
        return noteService.addNoteToUserById(id, text);
    }

    @RequestMapping(value = "/users/{id:[\\d]+}/notes", method = RequestMethod.GET)
    public List<Note> getAllUserNotes(@PathVariable Integer id) {
        return noteService.getAllUserNotesById(id);
    }

    @RequestMapping(value = "/users/{id:[\\d]+}/notes", method = RequestMethod.DELETE)
    public void removeAllUserNotes(@PathVariable Integer id) {
        noteService.removeAllUserNotes(id);
    }

    @RequestMapping(value = "/users/{id:[\\d]+}/notes/{noteId:[\\d]+}", method = RequestMethod.DELETE)
    public void removeUserNoteById(@PathVariable Integer noteId) {
        noteService.removeNoteById(noteId);
    }

}
