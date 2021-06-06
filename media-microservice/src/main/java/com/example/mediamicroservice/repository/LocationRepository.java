package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{
}
