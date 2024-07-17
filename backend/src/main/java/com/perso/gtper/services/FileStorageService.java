package com.perso.gtper.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perso.gtper.entities.FileStorage;
import com.perso.gtper.repositories.FileStorageRepository;

import java.io.IOException;

@Service
public class FileStorageService {

  @Autowired
  private FileStorageRepository fileStorageRepository;

  public String storeFile(MultipartFile file) throws IOException {
    FileStorage fileStorage = new FileStorage();
    fileStorage.setFilename(file.getOriginalFilename());
    fileStorage.setContentType(file.getContentType());
    fileStorage.setData(file.getBytes());

    fileStorage = fileStorageRepository.save(fileStorage);

    return fileStorage._id.toHexString();
  }

  public FileStorage getFile(ObjectId id) {
    return fileStorageRepository.findById(id).orElse(null);
  }
}
