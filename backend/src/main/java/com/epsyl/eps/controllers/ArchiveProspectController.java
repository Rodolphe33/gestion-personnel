package com.epsyl.eps.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.epsyl.eps.entities.ArchiveProspect;
import com.epsyl.eps.services.ArchiveProspectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/archive")
public class ArchiveProspectController {

  @Autowired
  private ArchiveProspectService archiveProspectService;

  // GET api/v1/archive/all
  @GetMapping("/all")
  public Iterable<ArchiveProspect> getAllArchive() {
    return archiveProspectService.listAll();
  }
  
  // GET api/v1/archive/id
  @GetMapping("/{id}")
  public ArchiveProspect getArchive(@PathVariable String id) {
    return archiveProspectService.getArchiveProspectByID(id).get();
  }

  // POST api/v1/archive/save 
  @RequestMapping("/save")
  public ArchiveProspect saveArchive(@RequestBody ArchiveProspect archive) {
    return archiveProspectService.save(archive);
  }

  // PUT api/v1/archive/id
  // @PutMapping("/{id}")
  // public ArchiveProspect updateArchive(@RequestBody ArchiveProspect archive, @PathVariable String id) {
  //   archive.set_id(id);
  //   archiveProspectService.save(archive);
  //   return archive;
  // }

  // DELETE api/v1/archive/delete/id
  @RequestMapping("/delete/{id}")
  public ResponseEntity<Void> deleteArchive(@PathVariable String _id) {
    archiveProspectService.deleteArchive(_id);
    return ResponseEntity.noContent().build();
  }
}
