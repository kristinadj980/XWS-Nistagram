package com.nistagram.profileMicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	Person findByEmail(String email);
	Person findByUsername(String username);
	Optional<Person> findById(Long id);
}
