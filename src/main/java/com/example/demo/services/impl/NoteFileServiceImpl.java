package com.example.demo.services.impl;

import com.example.demo.models.NoteFile;
import com.example.demo.repositories.FileRepository;
import com.example.demo.services.NoteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by maksim on 6/22/17.
 */
@Service
public class NoteFileServiceImpl implements NoteFileService {

    private FileRepository fileRepository;

    @Autowired
    public NoteFileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public NoteFile getById(Integer fileId) {
        return fileRepository.findOne(fileId);
    }

    @Override
    public List<NoteFile> getAll() {
        return null;
    }
}
