package com.example.searchmicroservice.connection;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.searchmicroservice.model.Tag;

@FeignClient(name="profileMicroservice")
public interface ProfileConnection {
	@RequestMapping(method=RequestMethod.GET,value="/api/profile/getPublicProfiles",consumes="application/json")
	List<String> getPublicProfiles();

}
