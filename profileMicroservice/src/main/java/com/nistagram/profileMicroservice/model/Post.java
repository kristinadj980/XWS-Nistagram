package Model;

import java.util.Date;
import java.util.List;

public class Post {
	
	private String description;
	private List<Profile> likes;
	private List<Profile> dislikes;
	private String comment;
	private Date date;
	
	public Post() {
		super();
	}

	public Post(String description, List<Profile> likes, List<Profile> dislikes, String comment, Date date) {
		super();
		this.description = description;
		this.likes = likes;
		this.dislikes = dislikes;
		this.comment = comment;
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Profile> getLikes() {
		return likes;
	}

	public void setLikes(List<Profile> likes) {
		this.likes = likes;
	}

	public List<Profile> getDislikes() {
		return dislikes;
	}

	public void setDislikes(List<Profile> dislikes) {
		this.dislikes = dislikes;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
