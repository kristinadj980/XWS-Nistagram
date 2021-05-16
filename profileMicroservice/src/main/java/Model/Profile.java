package Model;

import java.util.List;

public class Profile extends Person{
	
	private ProfileStatus profileStatus;
	private List<Post> favourites;
	private List<Profile> closeFriends;
	private String website;
	private String biography;
	private List<Profile> followers;
	private List<Profile> mutedFriends;
	private List<Profile> blockedUsers;
	private RequestStatus agentRequestStatus;
	
	public Profile() {
		super();
	}
	
	public Profile(ProfileStatus profileStatus, List<Post> favourites, List<Profile> closeFriends, String website,
			String biography, List<Profile> followers, List<Profile> mutedFriends, List<Profile> blockedUsers,
			RequestStatus agentRequestStatus) {
		super();
		this.profileStatus = profileStatus;
		this.favourites = favourites;
		this.closeFriends = closeFriends;
		this.website = website;
		this.biography = biography;
		this.followers = followers;
		this.mutedFriends = mutedFriends;
		this.blockedUsers = blockedUsers;
		this.agentRequestStatus = agentRequestStatus;
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
	
	public RequestStatus getAgentRequestStatus() {
		return agentRequestStatus;
	}
	
	public void setAgentRequestStatus(RequestStatus agentRequestStatus) {
		this.agentRequestStatus = agentRequestStatus;
	}
	
	
	
	

}
