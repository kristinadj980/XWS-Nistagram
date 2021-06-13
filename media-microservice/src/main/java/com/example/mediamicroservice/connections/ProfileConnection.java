package com.example.mediamicroservice.connections;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mediamicroservice.dto.PostDTO;

@FeignClient(name="profileMicroservice")
public interface ProfileConnection {
	
	@RequestMapping(method=RequestMethod.POST,value="/api/profile/proba",consumes="application/json")
	String proba();

}
