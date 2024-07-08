package com.epsyl.eps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsyl.eps.entities.Role;
import com.epsyl.eps.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Iterable<Role> getAllRoles() {
    return this.roleRepository.findAll();
  }

}
