package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mediamicroservice.model.ProfileMedia;

public interface ProfileMediaRepository extends JpaRepository<ProfileMedia, Long>{
	ProfileMedia findByUsername(String username);
	
	
	@Query(value="SELECT pm.username FROM profile_media pm JOIN post p WHERE p.id = ?1",nativeQuery=true)
	String findByPostId(Long id);
}
