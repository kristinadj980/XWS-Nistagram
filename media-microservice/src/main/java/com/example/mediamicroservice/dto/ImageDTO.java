package com.example.mediamicroservice.dto;

import java.util.List;

public class ImageDTO {
	private List<byte[]> imageBytes;

	public ImageDTO() {
		super();
	}

	public ImageDTO(List<byte[]> imageBytes) {
		super();
		this.imageBytes = imageBytes;
	}

	public List<byte[]> getImageBytes() {
		return imageBytes;
	}

	public void setImageBytes(List<byte[]> imageBytes) {
		this.imageBytes = imageBytes;
	}
	
	
}
