package com.example.mediamicroservice.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("Profile")
public class Profile extends Person {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   @Enumerated(EnumType.ORDINAL)
	   private ProfileStatus profileStatus;

	   @ManyToMany(targetEntity = Post.class,  cascade = CascadeType.ALL)
	   private List<Post> favourites;

	   @ManyToMany(targetEntity = Profile.class,  cascade = CascadeType.ALL)
	   private List<Profile> closeFriends;

		@Column(name = "website", nullable = false)
		private String website;

		@Column(name = "biography", nullable = false)
		private String biography;

	@ManyToMany(targetEntity = Profile.class,  cascade = CascadeType.ALL)
	   private List<Profile> followers;

	@ManyToMany(targetEntity = Profile.class,  cascade = CascadeType.ALL)
	   private List<Profile> mutedFriends;

	@ManyToMany(targetEntity = Profile.class,  cascade = CascadeType.ALL)
	private List<Profile> blockedUsers;

	@ManyToMany(targetEntity = Story.class,  cascade = CascadeType.ALL)
	   public List<Story> stories;

	@ManyToMany(targetEntity = Post.class,  cascade = CascadeType.ALL)
	   public List<Post> posts;
	   
	   
		public Profile(Long id, String username, String name, String surname, String email, String password, String role,
					   String phoneNumber, LocalDate birthDate, Gender gender, ProfileStatus profileStatus, List<Post> favourites,
					   List<Profile> closeFriends, String website, String biography, List<Profile> followers,
					   List<Profile> mutedFriends, List<Profile> blockedUsers, List<Story> story , List<Post>post) {
			super(id, username, name, surname, email, password, role, phoneNumber, birthDate, gender);
			this.profileStatus = profileStatus;
			this.favourites = favourites;
			this.closeFriends = closeFriends;
			this.website = website;
			this.biography = biography;
			this.followers = followers;
			this.mutedFriends = mutedFriends;
			this.blockedUsers = blockedUsers;
			this.stories = story;
			this.posts = post;
		}

	public Profile() {

	}

	public ProfileStatus getProfileStatus() {
			return profileStatus;
		}


		public void setProfileStatus(ProfileStatus profileStatus) {
			this.profileStatus = profileStatus;
		}


		public List<Post> getFavourites() {
			return favourites;
		}


		public void setFavourites(List<Post> favourites) {
			this.favourites = favourites;
		}


		public List<Profile> getCloseFriends() {
			return closeFriends;
		}


		public void setCloseFriends(List<Profile> closeFriends) {
			this.closeFriends = closeFriends;
		}


		public String getWebsite() {
			return website;
		}


		public void setWebsite(String website) {
			this.website = website;
		}


		public String getBiography() {
			return biography;
		}


		public void setBiography(String biography) {
			this.biography = biography;
		}


		public List<Profile> getFollowers() {
			return followers;
		}


		public void setFollowers(List<Profile> followers) {
			this.followers = followers;
		}


		public List<Profile> getMutedFriends() {
			return mutedFriends;
		}


		public void setMutedFriends(List<Profile> mutedFriends) {
			this.mutedFriends = mutedFriends;
		}


		public List<Profile> getBlockedUsers() {
			return blockedUsers;
		}


		public void setBlockedUsers(List<Profile> blockedUsers) {
			this.blockedUsers = blockedUsers;
		}


		public List<Story> getStory() {
			return stories;
		}


		public void setStory(List<Story> story) {
			this.stories = story;
		}


		public List<Post> getPost() {
			return posts;
		}


		public void setPost(List<Post> post) {
			this.posts = post;
		}

   
}