package com.example.searchmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.searchmicroservice.model.Location;

public interface LocationRepository extends JpaRepository<Location,Long> {

}
