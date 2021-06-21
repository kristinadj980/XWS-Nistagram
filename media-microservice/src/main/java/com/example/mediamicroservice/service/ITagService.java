package com.example.mediamicroservice.service;

import java.util.List;

import com.example.mediamicroservice.model.Tag;

public interface ITagService {
	List<Tag> findAll();
	Tag getTag(String tag);
}

