package com.nistagram.profileMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.VerificationRequest;

public interface VerificationRequestrepository extends JpaRepository<VerificationRequest, Long>{
}
