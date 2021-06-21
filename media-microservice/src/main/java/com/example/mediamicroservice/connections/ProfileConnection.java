package com.example.mediamicroservice.connections;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="profileMicroservice")
public interface ProfileConnection {
	
	@RequestMapping(method=RequestMethod.POST,value="/api/profile/proba",consumes="application/json")
	String proba();
	
	@RequestMapping(method=RequestMethod.POST,value="/api/notifications/postNotify",consumes="application/json")
	String postNotify(String username);

	@RequestMapping(method=RequestMethod.POST,value="/api/notifications/storyNotify",consumes="application/json")
	String storyNotify(String username);

}
