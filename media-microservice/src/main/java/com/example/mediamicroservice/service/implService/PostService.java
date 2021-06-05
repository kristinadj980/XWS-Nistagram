package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.MediaDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.TagDTO;
import com.example.mediamicroservice.model.Location;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.PostRepository;
import com.example.mediamicroservice.service.IPostService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
	
	
	private final PostRepository postRepository;
	
	@Autowired
	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	@Override
	public Post addNewPost(PostDTO postDTO) {
        Post post = new Post();
        post.setDescription(postDTO.getDescription());
        String locationFront= postDTO.getLocation();
        String parts[] = locationFront.split(",");
        String country = parts[0];
        String city = parts[1];
        String objectName = parts[2];
        String street = parts[3];
        
        Location location = new Location(city,street , country,objectName);
        post.setLocation(location);
       /* List<TagDTO> tagsDTO = postDTO.getTags();
        List<Tag> tags = new ArrayList<Tag>();
        for (TagDTO t : tagsDTO) {
			Tag tag = new Tag(t.getName());
			tags.add(tag);
		}
        post.setTags(tags);*/
        Media media = new Media();
        media.setFileName(postDTO.getFileName());
        List<Media> medias = new ArrayList<Media>();
        medias.add(media);
        post.setMedia(medias);
        post.setDate(LocalDate.now());
        
		return postRepository.save(post);
	}
	
}
