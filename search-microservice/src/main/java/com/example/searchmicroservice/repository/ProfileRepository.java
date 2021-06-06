package com.example.searchmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.searchmicroservice.model.Profile;
public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
