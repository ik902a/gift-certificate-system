package com.epam.esm.response;

import java.util.List;

public class JwtResponse {
	private String token;
//	private String type = "Bearer";
	private Long id;
	private String login;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.login = username;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

//	public String getTokenType() {
//		return type;
//	}
//
//	public void setTokenType(String tokenType) {
//		this.type = tokenType;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return login;
	}

	public void setUsername(String username) {
		this.login = username;
	}

	public List<String> getRoles() {
		return roles;
	}

}
