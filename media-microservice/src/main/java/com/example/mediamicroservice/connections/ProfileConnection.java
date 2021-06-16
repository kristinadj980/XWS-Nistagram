package com.example.mediamicroservice.connections;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name="profileMicroservice")
public interface ProfileConnection {
	
	@RequestMapping(method=RequestMethod.GET,value="/api/profile/getCloseFriends",consumes="application/json")
	List<String> getCloseFriends(String username);

}
