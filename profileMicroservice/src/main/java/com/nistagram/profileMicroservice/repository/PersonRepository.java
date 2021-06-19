package com.nistagram.profileMicroservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nistagram.profileMicroservice.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	Person findByEmail(String email);
	Person findByUsername(String username);
	Optional<Person> findById(Long id);
	
	@Query(value="SELECT username FROM person WHERE profile_status=1",nativeQuery=true)
	List<String> getPublicProfiles();
	
	@Query(value="select username from person p join profile_following pf where pf.followers_id=p.id and pf.profile_id=?1 and p.profile_status=0",nativeQuery=true)
	List<String> getPrivateProfiles(Long id);

}
