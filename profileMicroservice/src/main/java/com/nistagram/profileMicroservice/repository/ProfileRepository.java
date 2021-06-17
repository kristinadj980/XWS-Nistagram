package com.nistagram.profileMicroservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nistagram.profileMicroservice.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
	Profile findByEmail(String email);
	Profile findByUsername(String username);
	Optional<Profile> findById(Long id);
	
	
	@Query(value = "select username " +
            "from person ", nativeQuery = true)
    List<String> findAllUsernames();
}
