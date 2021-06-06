package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
}
