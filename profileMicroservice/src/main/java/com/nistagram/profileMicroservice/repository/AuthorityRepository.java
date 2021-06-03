package com.nistagram.profileMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{

}
