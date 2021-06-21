package com.example.mediamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mediamicroservice.model.ProfileMedia;

public interface ProfileMediaRepository extends JpaRepository<ProfileMedia, Long>{
	ProfileMedia findByUsername(String username);

	
	
	@Query(value="select pm.username from profile_media pm join profile_media_posts p where pm.id=p.profile_media_id and p.posts_id=?1",nativeQuery=true)
	String findByPostId(Long id);

	List<ProfileMedia> findAll();

}
