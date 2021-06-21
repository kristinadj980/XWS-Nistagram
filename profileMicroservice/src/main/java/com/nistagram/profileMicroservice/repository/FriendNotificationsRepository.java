package com.nistagram.profileMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.FriendNotifications;

public interface FriendNotificationsRepository extends JpaRepository<FriendNotifications, Long>{

}
