package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.ProfileMedia;

public interface ProfileMediaRepository extends JpaRepository<ProfileMedia, Long>{
	ProfileMedia findByUsername(String username);
}
