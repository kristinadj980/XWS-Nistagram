package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.TagRepository;
import com.example.mediamicroservice.service.ITagService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService implements ITagService {

	@Autowired
	private final TagRepository tagRepository;
	
	
	
	
	public TagService(TagRepository tagRepository) {
		super();
		this.tagRepository = tagRepository;
	}




	@Override
	public List<Tag> findAll() {
		List<Tag> allTags=tagRepository.findAll();
		
		return allTags;
	}




	@Override
	public Tag getTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}



}
