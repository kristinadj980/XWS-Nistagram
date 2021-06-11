package com.example.mediamicroservice.service;

import java.util.List;

import com.example.mediamicroservice.dto.LocationDTO;

public interface ILocationService {
	
	List<LocationDTO> getLocations();
}
