package com.example.demo.services.impl;

import com.example.demo.models.File;
import com.example.demo.repositories.FileRepository;
import com.example.demo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by maksim on 6/22/17.
 */
@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File getById(Integer id) {
        return fileRepository.findOne(id);
    }
}
