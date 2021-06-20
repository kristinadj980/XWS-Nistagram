package com.example.mediamicroservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mediamicroservice.dto.MediaDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.CommentDTO;
import com.example.mediamicroservice.dto.InapropriateContentDTO;
import com.example.mediamicroservice.dto.LikeDislikeInfoDTO;
import com.example.mediamicroservice.dto.LikePostDTO;

import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.service.implService.PostService;
import com.example.mediamicroservice.service.implService.ProfileMediaService;
import com.example.mediamicroservice.utils.MediaUpload;

@RestController
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
	
	private final PostService postService;
	private final ProfileMediaService profileMediaService;

	@Autowired
	public PostController(PostService postService,ProfileMediaService profileMediaService) {
		super();
		this.postService = postService;
		this.profileMediaService = profileMediaService;
	}
	
	private static String uploadDir = "user-photos";
	private static String uploadDir2 = "verification-photos";

	@PostMapping("/saveImage")
    public List<String> saveImage(@RequestParam("file") List<MultipartFile> multipartFiles ) throws IOException {
		List<String> fileNames = new ArrayList<String>();
		for(MultipartFile multipartFile:multipartFiles) {
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", "")); 
	        fileNames.add(fileName);
	        uploadDir = "user-photos";
	        MediaUpload.saveFile(uploadDir, fileName, multipartFile);
		}
        return fileNames;
    }
	
	@PostMapping("/saveImageForRequest")
    public String saveImageForRequest(@RequestParam("file") MultipartFile multipartFile ) throws IOException {
		
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", "")); 
        uploadDir2 = "verification-photos";
        MediaUpload.saveFile(uploadDir2, fileName, multipartFile);
        return fileName;
    }
	
	@PostMapping("/addNewPost")
	public ResponseEntity<Post> addNewPost(@RequestBody PostDTO postDTO) {
        Post response = postService.addNewPost(postDTO);
        
		return (ResponseEntity<Post>) (response == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(response));
		
	}
	

	@GetMapping("/getMyPosts/{username}")
	public ResponseEntity getMyPosts(@PathVariable String username) {
		
		return new ResponseEntity(postService.findMyPosts(username), HttpStatus.OK); 

	}
	
	@PostMapping("/likePost")
	public int likePost(@RequestBody LikePostDTO likePostDTO) {
        int likes = postService.likeThisPost(likePostDTO);
        
		return likes;
		
	}
	
	@PostMapping("/dislikePost")
	public int dislikePost(@RequestBody LikePostDTO likePostDTO) {
        int dislikes = postService.dislikeThisPost(likePostDTO);
        
		return dislikes;
		
	}
	
	@GetMapping("/proba")
	public String getProba() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		return "uspesno";
	}
	
	
	@PostMapping("/getFriendsPosts")
	public ResponseEntity<List<PostDTO>> getFriendsPosts(@RequestBody List<PostDTO> postDTOs) {
		System.out.println("USPELOOOOOOOOOOOOOOOO");
		try {
			List<PostDTO> posts = new ArrayList<PostDTO>();
			System.out.println("USPELOOOOOOOOOOOOOOOO");
			for(PostDTO p:postDTOs) {
				System.out.println("USPELOOOOOOOOOOOOOOOO");
				List<PostDTO> friendPosts = new ArrayList<PostDTO>();
				System.out.println("USPELOOOOOOOOOOOOOOOO"+p.getFollowing());
				friendPosts = postService.findMyPosts(p.getFollowing());
				System.out.println("USPELOOOOOOOOOOOOOOOO");
				for(PostDTO pf:friendPosts) {
					System.out.println("USPELOOOOOOOOOOOOOOOO");
					posts.add(pf);
				}
				
			}
			System.out.println("USPELOOOOOOOOOOOOOOOO");
			posts = postService.sortByDate(posts);
			return new ResponseEntity(posts, HttpStatus.OK); 
		}catch(Exception e) {
			System.out.println("USPELOOOOOOOOOOOOOOOO" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/getMyLikesInfo")
    public ResponseEntity getMyLikesInfo(@RequestBody LikeDislikeInfoDTO infoDTO) {
		
		return new ResponseEntity(postService.findMyLikes(infoDTO), HttpStatus.OK); 

	}
	
	@PostMapping("/getMyDislikesInfo")
    public ResponseEntity getMyDislikesInfo(@RequestBody LikeDislikeInfoDTO infoDTO) {
		
		return new ResponseEntity(postService.findMyDislikes(infoDTO), HttpStatus.OK); 

	}
	
	@PostMapping("/commentPost")
	public ResponseEntity commentPost(@RequestBody LikePostDTO likePostDTO) {
		postService.commentPost(likePostDTO);
		
		return new ResponseEntity(likePostDTO, HttpStatus.OK);
	}
	
	@PostMapping("/getMyCommentsInfo")
    public ResponseEntity getMyCommentsInfo(@RequestBody LikePostDTO infoDTO) {
		
		return new ResponseEntity(postService.findMyComments(infoDTO), HttpStatus.OK); 

	}
	
	@GetMapping("/getMyFavouritePosts/{username}")
	public ResponseEntity getMyFavouritePosts(@PathVariable String username) {
		
		return new ResponseEntity(postService.findMyFavouritePosts(username), HttpStatus.OK); 

	}
	
	@PostMapping("/reportPost")
	public ResponseEntity reportPost(@RequestBody InapropriateContentDTO dto) {
		
		return new ResponseEntity(postService.reportPost(dto), HttpStatus.OK); 
	}
	
	@GetMapping("/getLikeDislikeHistory/{username}")
	public ResponseEntity getMyLikeDislikeHistory(@PathVariable String username) {
		
		return new ResponseEntity(postService.getMyLikeDislikeHistory(username), HttpStatus.OK); 

	}
	
	@PostMapping("/findPostPictureById")
	public ResponseEntity findPostPictureById(@RequestBody PostDTO dto) {
		
		return new ResponseEntity(postService.findPostPictureById(dto.getId(),dto.getUsername()), HttpStatus.OK); 
	}
	

}
