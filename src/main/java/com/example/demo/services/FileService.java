package com.example.demo.services;

import com.example.demo.models.NoteFile;

/**
 * Created by maksim on 6/22/17.
 */
public interface FileService {
    NoteFile getById(Integer fileId);
}
