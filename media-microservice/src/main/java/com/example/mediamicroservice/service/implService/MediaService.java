package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.repository.MediaRepository;
import com.example.mediamicroservice.service.IMediaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService implements IMediaService {
	
	private final MediaRepository mediaRepository;
	
	@Autowired
	public MediaService(MediaRepository mediaRepository) {
		this.mediaRepository = mediaRepository;
	}
	
	public Media saveDocumentImage(String fileName) {
		Media media = new Media();
		media.setFileName(fileName);
		
		return mediaRepository.save(media);
	}
}
