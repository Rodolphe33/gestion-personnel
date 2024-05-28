package com.epsyl.eps.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsyl.eps.entities.UserEntity;
import com.epsyl.eps.repositories.UserRepository;

@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;

	public Optional<UserEntity> getUserByID(String userId) {
		return this.userRepository.findById(userId);
	}

	public Iterable<UserEntity> listAll() {
		return this.userRepository.findAll();
	}

	public UserEntity saveOrUpdateUser(UserEntity user) {
		return this.userRepository.save(user);
  }

	public Optional<UserEntity> deleteUser(String _id) {
		return this.userRepository.findById(_id).flatMap(user -> {
      userRepository.deleteById(user.get_id());
      return Optional.of(user);
    });
	}

}
