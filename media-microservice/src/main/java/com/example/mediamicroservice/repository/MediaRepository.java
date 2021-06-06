package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Media;

public interface MediaRepository extends JpaRepository<Media, Long>{
}
