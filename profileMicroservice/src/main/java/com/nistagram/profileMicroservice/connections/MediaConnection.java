package com.nistagram.profileMicroservice.connections;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nistagram.profileMicroservice.dto.EditUsernameDTO;
import com.nistagram.profileMicroservice.dto.PostDTO;
import com.nistagram.profileMicroservice.dto.VerificationRequestDTO;
import com.nistagram.profileMicroservice.model.Media;


@FeignClient(name = "media-microservice")
public interface MediaConnection {
	@GetMapping("/getMyPosts/{username}")
	ResponseEntity<List<PostDTO>> getMyPosts(@PathVariable String username);
	
	@RequestMapping(method=RequestMethod.GET,value="/post/proba",consumes="application/json")
	String getProba();
	
	@RequestMapping(method=RequestMethod.POST,value="/profile/changeUsername",consumes="application/json")
	void changeUsername(EditUsernameDTO editUsernameDTO);
	
	@RequestMapping(method=RequestMethod.POST,value="/media/saveDocument",consumes="application/json")
	Media saveVerificationDocument(@RequestBody String fileName);
	
	

}
