package com.epsyl.eps.services;

import com.epsyl.eps.entities.FileStorageEntity;
import com.epsyl.eps.repositories.FileStorageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {

  @Autowired
  private FileStorageRepository fileStorageRepository;

  public String storeFile(MultipartFile file) throws IOException {
    FileStorageEntity fileStorage = new FileStorageEntity();
    fileStorage.setFilename(file.getOriginalFilename());
    fileStorage.setContentType(file.getContentType());
    fileStorage.setData(file.getBytes());

    fileStorage = fileStorageRepository.save(fileStorage);

    return fileStorage.get_id();
  }

  public FileStorageEntity getFile(String id) {
    return fileStorageRepository.findById(id).orElse(null);
  }
}
