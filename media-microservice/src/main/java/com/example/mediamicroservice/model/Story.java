package com.example.mediamicroservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Story {


	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private Long id;

	   @Column(name = "description", nullable = false)
	   private String description;

	  @Column(name = "startTime", nullable = false)
	  private Date startTime;

	@Column(name = "endTime", nullable = false)
	   private Date endTime;

	 @Column(name = "visible", nullable = false)
	   private boolean visible;

	@Column(name = "date", nullable = false)
	   private Date date;

	@Column(name = "highlighted", nullable = false)
	   private boolean highlighted;

	  @Column(name = "numberOfInappContent", nullable = false)
	   private int numberOfInappropriateContent;

	   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JoinTable(name = "story_medias",
	   joinColumns = @JoinColumn(name = "story_id", referencedColumnName = "id"),
	   inverseJoinColumns = @JoinColumn(name = "medias_id", referencedColumnName = "id"))
	   public List<Media> medias;

	   @ManyToMany(mappedBy = "stories")
	   public List<Tag> tags;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Location location;
	   
	   
	   
	   public Story(Long id,String description, Date startTime, Date endTime, boolean visible, Date date, boolean highlighted,
			int numberOfInappropriateContent, List<Media> media, List<Tag> tag, Location location) {
			super();
			this.id=id;
			this.description = description;
			this.startTime = startTime;
			this.endTime = endTime;
			this.visible = visible;
			this.date = date;
			this.highlighted = highlighted;
			this.numberOfInappropriateContent = numberOfInappropriateContent;
			this.medias = media;
			this.tags = tag;
			this.location = location;
	}

	public Story() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return medias;
	}



	public void setMedia(List<Media> media) {
		this.medias = media;
	}



	public List<Tag> getTag() {
		return tags;
	}



	public void setTag(List<Tag> tag) {
		this.tags = tag;
	}



	public Location getLocation() {
		return location;
	}



	public void setLocation(Location location) {
		this.location = location;
	}
	   
	   
	   
}
