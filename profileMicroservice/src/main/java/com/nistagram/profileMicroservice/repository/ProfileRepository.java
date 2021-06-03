package com.nistagram.profileMicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
	Profile findByEmail(String email);
	Optional<Profile> findById(Long id);
}
