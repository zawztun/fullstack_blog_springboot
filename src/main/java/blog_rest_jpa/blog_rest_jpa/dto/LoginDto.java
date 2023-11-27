package blog_rest_jpa.blog_rest_jpa.dto;

public class LoginDto {
	private String username;
	private String password;
	private String email;

	public LoginDto() {
	}
	public LoginDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public LoginDto(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	// Getters
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	// Setters
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// toString
	@Override
	public String toString() {
		return "RegisterDto{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
