package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.InappropriateContent;

public interface InappropriateContentRepository extends JpaRepository<InappropriateContent, Long>{
}
