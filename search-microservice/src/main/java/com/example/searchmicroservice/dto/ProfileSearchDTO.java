package com.example.searchmicroservice.dto;

public class ProfileSearchDTO {

	private String searchData;

	public ProfileSearchDTO() {
		super();
	}

	public ProfileSearchDTO(String searchData) {
		super();
		this.searchData = searchData;
	}

	public String getSearchData() {
		return searchData;
	}

	public void setSearchData(String searchData) {
		this.searchData = searchData;
	}

	
}
