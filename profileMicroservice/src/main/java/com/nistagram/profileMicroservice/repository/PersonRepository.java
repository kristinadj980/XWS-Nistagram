package com.nistagram.profileMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
}
