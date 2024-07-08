package com.epsyl.eps.services;

import com.epsyl.eps.entities.FileStorage;
import com.epsyl.eps.repositories.FileStorageRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
