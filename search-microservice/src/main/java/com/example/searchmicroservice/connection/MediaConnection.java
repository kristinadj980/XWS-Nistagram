package com.example.searchmicroservice.connection;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.searchmicroservice.dto.LocationDTO;
import com.example.searchmicroservice.dto.PostDTO;
import com.example.searchmicroservice.model.Location;
import com.example.searchmicroservice.model.Post;
import com.example.searchmicroservice.model.Tag;

@FeignClient(name="media-microservice")
public interface MediaConnection {
	@RequestMapping(method=RequestMethod.GET,value="/tag/getAllTags",consumes="application/json")
	List<Tag> getAllTags();
	
	@RequestMapping(method=RequestMethod.GET,value="/tag/findPostsByTag/{tag}",consumes="application/json")
	List<PostDTO> findPostsByTag(@PathVariable String tag);
	
	@RequestMapping(method=RequestMethod.GET,value="/location/getLocations",consumes="application/json")
	List<Location> getLocations();
	
	@RequestMapping(method=RequestMethod.GET,value="/location/findPostsByLocation/{location}",consumes="application/json")
	List<PostDTO> findPostsByLocation(@PathVariable String location);
	
}