package com.nistagram.profileMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.Administrator;


public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
