package com.lm2a.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lm2a.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	Optional<UserEntity> findByUsername(String username);
}
