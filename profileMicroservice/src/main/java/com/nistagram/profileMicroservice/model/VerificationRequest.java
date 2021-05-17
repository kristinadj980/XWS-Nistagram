package Model;

public class VerificationRequest {
	private String name;
	private String surname;
	private VerificationCategory category;
	
	public VerificationRequest() {
		super();
	}

	public VerificationRequest(String name, String surname, VerificationCategory category) {
		super();
		this.name = name;
		this.surname = surname;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public VerificationCategory getCategory() {
		return category;
	}

	public void setCategory(VerificationCategory category) {
		this.category = category;
	}
	
	

}
