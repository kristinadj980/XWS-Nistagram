package com.example.mediamicroservice.service;

import java.util.List;


import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.LikeDislikeHistoryDTO;
import com.example.mediamicroservice.dto.LikeDislikeInfoDTO;
import com.example.mediamicroservice.dto.LikePostDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Comment;
import com.example.mediamicroservice.model.Post;

public interface IPostService {
	
	Post addNewPost(PostDTO postDTO);
	List<PostDTO> findMyPosts(String username);
	List<PostDTO> findAllPosts();
	List<PostDTO> sortByDate(List<PostDTO> allPosts);
	List<PostDTO> findPostsByTag(String tag);
	List<PostDTO> findPostsByLocation(LocationDTO location);
	Integer likeThisPost(LikePostDTO likePostDTO);
	Integer dislikeThisPost(LikePostDTO likePostDTO);
	List<LikeDislikeInfoDTO> findMyLikes(LikeDislikeInfoDTO dtoInfo);
	List<LikeDislikeInfoDTO> findMyDislikes(LikeDislikeInfoDTO dtoInfo);
	void commentPost(LikePostDTO dto);
	List<LikePostDTO> findMyComments(LikePostDTO dtoInfo);
	List<Comment> sortCommentsByDate(List<Comment> comments);
	Post findById(Long id);
	List<PostDTO> findMyFavouritePosts(String username);
	List<LikeDislikeHistoryDTO> getMyLikeDislikeHistory(String username);
	List<PostDTO> findPostPictureById(Long postId, String username);
	List<PostDTO> getPublicProfilesPosts();
}
