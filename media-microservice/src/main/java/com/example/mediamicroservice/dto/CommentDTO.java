package com.example.mediamicroservice.dto;

public class CommentDTO {
	private String answer;
	private Long id;
	private boolean isAnswered;

	public CommentDTO() {
		super();
	}

	public CommentDTO(String answer, Long id, boolean isAnswered) {
		super();
		this.answer = answer;
		this.id = id;
		this.isAnswered = isAnswered;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

}
