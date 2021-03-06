package com.example.mediamicroservice.connections;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mediamicroservice.dto.CommentNotifyDTO;

@FeignClient(name="profileMicroservice")
public interface ProfileConnection {
	

	@RequestMapping(method=RequestMethod.POST,value="/api/notifications/postNotify",consumes="application/json")
	String postNotify(String username);

	@RequestMapping(method=RequestMethod.POST,value="/api/notifications/storyNotify",consumes="application/json")
	String storyNotify(String username);
	
	@RequestMapping(method=RequestMethod.POST,value="/api/notifications/commentNotify",consumes="application/json")
	String commentNotify(CommentNotifyDTO commentNotifyDTO);

	@RequestMapping(method=RequestMethod.GET,value="/api/profile/getCloseFriends",consumes="application/json")
	List<String> getCloseFriends(String username);
	
	@RequestMapping(method=RequestMethod.GET,value="/api/profile/getPublicProfiles",consumes="application/json")
	List<String> getPublicProfiles();

}
