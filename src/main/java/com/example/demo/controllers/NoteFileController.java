package com.example.demo.controllers;

import com.example.demo.models.Note;
import com.example.demo.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Admin on 26.06.2017.
 */
@RestController
public class NoteFileController {

    private final NoteService noteService;

    @Autowired
    public NoteFileController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = "/users/{userId:[\\d]+}/notes/{noteId:[\\d]+}/files", method = RequestMethod.GET)
    public void removeNoteById(@PathVariable Integer userId, @PathVariable Integer noteId) {
        noteService.removeNoteById(userId, noteId);
    }

}
