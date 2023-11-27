package blog_rest_jpa.blog_rest_jpa.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
	private String token;
	private String username;

	public LoginResponseDto(String token, String username) {
		this.token = token;
		this.username = username;
	}

	private String role;

}
