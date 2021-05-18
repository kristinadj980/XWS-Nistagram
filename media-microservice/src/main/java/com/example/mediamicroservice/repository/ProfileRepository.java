package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
}
