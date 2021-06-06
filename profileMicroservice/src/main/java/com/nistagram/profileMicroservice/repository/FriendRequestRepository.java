package com.nistagram.profileMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
}
