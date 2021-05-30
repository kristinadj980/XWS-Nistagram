package com.example.searchmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.searchmicroservice.model.Media;

public interface MediaRepository extends JpaRepository<Media,Long> {

}
