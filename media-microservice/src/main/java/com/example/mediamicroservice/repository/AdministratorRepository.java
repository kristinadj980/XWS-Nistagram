package com.example.mediamicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
