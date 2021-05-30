package com.example.searchmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.searchmicroservice.model.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
