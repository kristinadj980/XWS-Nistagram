package Model;

public class FriendRequest {
	
	private Profile profile;
	private FriendRequestStatus friendRequestStatus;
	
	public FriendRequest() {
		super();
	}

	public FriendRequest(Profile profile, FriendRequestStatus friendRequestStatus) {
		super();
		this.profile = profile;
		this.friendRequestStatus = friendRequestStatus;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public FriendRequestStatus getFriendRequestStatus() {
		return friendRequestStatus;
	}

	public void setFriendRequestStatus(FriendRequestStatus friendRequestStatus) {
		this.friendRequestStatus = friendRequestStatus;
	}
	
	

}
