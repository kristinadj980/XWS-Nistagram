package com.example.mediamicroservice.service;

import java.util.List;

import com.example.mediamicroservice.dto.LikeDislikeInfoDTO;
import com.example.mediamicroservice.dto.LikePostDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Post;

public interface IPostService {
	
	Post addNewPost(PostDTO postDTO);
	List<PostDTO> findMyPosts(String username);
	List<PostDTO> sortByDate(List<PostDTO> allPosts);
	Integer likeThisPost(LikePostDTO likePostDTO);
	Integer dislikeThisPost(LikePostDTO likePostDTO);
	List<LikeDislikeInfoDTO> findMyLikes(LikeDislikeInfoDTO dtoInfo);
	List<LikeDislikeInfoDTO> findMyDislikes(LikeDislikeInfoDTO dtoInfo);
	void commentPost(LikePostDTO dto);
	List<LikeDislikeInfoDTO> findMyComments(LikeDislikeInfoDTO dtoInfo);
}
