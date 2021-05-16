package Model;

public class Notification {
	private Boolean status;
	private Profile profile;
	
	public Notification() {
		super();
	}

	public Notification(Boolean status, Profile profile) {
		super();
		this.status = status;
		this.profile = profile;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	

}
