package com.nistagram.profileMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
}
