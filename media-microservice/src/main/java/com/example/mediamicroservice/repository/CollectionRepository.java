package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.FavouritesCollection;

public interface CollectionRepository extends JpaRepository<FavouritesCollection, Long>{

}
