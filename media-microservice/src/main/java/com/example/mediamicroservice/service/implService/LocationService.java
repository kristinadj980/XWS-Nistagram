package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.model.Location;
import com.example.mediamicroservice.repository.LocationRepository;
import com.example.mediamicroservice.service.ILocationService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService implements ILocationService {

	
	private final LocationRepository locationRepository;

	@Autowired
	public LocationService(LocationRepository locationRepository) {
		super();
		this.locationRepository = locationRepository;
	}



	@Override
	public List<LocationDTO> getLocations() {
		List<Location> locations = locationRepository.findAll();
		List<LocationDTO> locationsDTO = new ArrayList<>();
		for(Location l:locations) {
			locationsDTO.add(new LocationDTO(l.getId(), l.getCity(), l.getStreet(), l.getCountry(), l.getObjectName()));
		}
		
		return locationsDTO;
	}
	
	
}
