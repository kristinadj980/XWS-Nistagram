package com.nistagram.profileMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nistagram.profileMicroservice.model.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Authority findByName(String name);

}
