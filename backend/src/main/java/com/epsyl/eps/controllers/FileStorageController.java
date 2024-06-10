package com.epsyl.eps.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.epsyl.eps.entities.FileStorage;
import com.epsyl.eps.services.FileStorageService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/v1/files")
public class FileStorageController {

  private static final Logger logger = LoggerFactory.getLogger(FileStorageController.class);

  @Autowired
  private FileStorageService fileStorageService;

  @PostMapping("/upload")
  public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
    Map<String, String> response = new HashMap<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

    try {
      String fileId = fileStorageService.storeFile(file);
      response.put("fileId", fileId);
      logger.info("File uploaded successfully with ID: {}", fileId);
      return new ResponseEntity<>(response, headers, HttpStatus.OK);
    } catch (IOException exception) {
      response.put("error", "File upload failed");
      logger.error("File upload failed", exception);
      return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/download/{id}")
  public ResponseEntity<byte[]> downloadFile(@PathVariable String _id) {
    FileStorage fileStorage = fileStorageService.getFile(_id);
    if(fileStorage != null) {
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""  + fileStorage.getFilename() + "\"");
      return new ResponseEntity<>(fileStorage.getData(), headers, HttpStatus.OK);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

}
