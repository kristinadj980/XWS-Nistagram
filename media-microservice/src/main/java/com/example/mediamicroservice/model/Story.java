package com.example.mediamicroservice.model;

import java.util.Date;
import java.util.List;

public class Story {

	   private String description;
	   private Date startTime;
	   private Date endTime;
	   private boolean visible;
	   private Date date;
	   private boolean highlighted;
	   private int numberOfInappropriateContent;
	   
	   public List<Media> media;
	   public List<Tag> tag;
	   public Location location;
	   
	   
	   
	   public Story(String description, Date startTime, Date endTime, boolean visible, Date date, boolean highlighted,
			int numberOfInappropriateContent, List<Media> media, List<Tag> tag, Location location) {
			super();
			this.description = description;
			this.startTime = startTime;
			this.endTime = endTime;
			this.visible = visible;
			this.date = date;
			this.highlighted = highlighted;
			this.numberOfInappropriateContent = numberOfInappropriateContent;
			this.media = media;
			this.tag = tag;
			this.location = location;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public Date getEndTime() {
		return endTime;
	}



	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	public boolean isVisible() {
		return visible;
	}



	public void setVisible(boolean visible) {
		this.visible = visible;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public boolean isHighlighted() {
		return highlighted;
	}



	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}



	public int getNumberOfInappropriateContent() {
		return numberOfInappropriateContent;
	}



	public void setNumberOfInappropriateContent(int numberOfInappropriateContent) {
		this.numberOfInappropriateContent = numberOfInappropriateContent;
	}



	public List<Media> getMedia() {
		return media;
	}



	public void setMedia(List<Media> media) {
		this.media = media;
	}



	public List<Tag> getTag() {
		return tag;
	}



	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}



	public Location getLocation() {
		return location;
	}



	public void setLocation(Location location) {
		this.location = location;
	}
	   
	   
	   
}
