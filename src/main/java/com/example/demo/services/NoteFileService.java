package com.example.demo.services;

import com.example.demo.models.NoteFile;

import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
public interface NoteFileService {
    NoteFile getById(Integer fileId);

    List<NoteFile> getAll();
}
