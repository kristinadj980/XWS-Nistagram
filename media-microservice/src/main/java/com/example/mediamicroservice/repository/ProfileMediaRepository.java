package com.example.mediamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.ProfileMedia;

public interface ProfileMediaRepository extends JpaRepository<ProfileMedia, Long>{
	ProfileMedia findByUsername(String username);
	List<ProfileMedia> findAll();
}
