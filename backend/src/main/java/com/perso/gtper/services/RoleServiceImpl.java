package com.perso.gtper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.gtper.entities.Role;
import com.perso.gtper.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Iterable<Role> getAllRoles() {
    return this.roleRepository.findAll();
  }

}
