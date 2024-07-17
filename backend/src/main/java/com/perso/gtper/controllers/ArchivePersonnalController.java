package com.perso.gtper.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.perso.gtper.entities.ArchivePersonnal;
import com.perso.gtper.services.ArchivePersonnalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/archive")
public class ArchivePersonnalController {

  @Autowired
  private ArchivePersonnalService archivePersonnalService;

  // GET api/v1/archive/all
  @GetMapping("/all")
  public Iterable<ArchivePersonnal> getAllArchive() {
    return archivePersonnalService.listAll();
  }
  
  // GET api/v1/archive/id
  @GetMapping("/{id}")
  public ArchivePersonnal getArchive(@PathVariable String id) {
    return archivePersonnalService.getArchivePersonnalByID(id).get();
  }

  // POST api/v1/archive/save 
  @RequestMapping("/save")
  public ArchivePersonnal saveArchive(@RequestBody ArchivePersonnal archive) {
    return archivePersonnalService.save(archive);
  }

  // PUT api/v1/archive/id
  // @PutMapping("/{id}")
  // public ArchivePersonnal updateArchive(@RequestBody ArchivePersonnal archive, @PathVariable String id) {
  //   archive.set_id(id);
  //   archivePersonnalService.save(archive);
  //   return archive;
  // }

  // DELETE api/v1/archive/delete/id
  @RequestMapping("/delete/{id}")
  public ResponseEntity<Void> deleteArchive(@PathVariable String _id) {
    archivePersonnalService.deleteArchive(_id);
    return ResponseEntity.noContent().build();
  }
}
