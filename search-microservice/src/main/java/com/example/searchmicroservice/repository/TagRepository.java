package com.example.searchmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.searchmicroservice.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {

}
