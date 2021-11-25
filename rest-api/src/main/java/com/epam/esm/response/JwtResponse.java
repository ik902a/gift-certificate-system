package com.epam.esm.response;

import java.util.List;

/**
 * The {@code JwtResponse} class describes the response JSON web token
 * 
 * @author Ihar Klepcha
 */
public class JwtResponse {
	private String token;
	private String type;
	private Long id;
	private String login;
	private List<String> roles;

    /**
	 * Constructs a new response
	 */
	public JwtResponse() {
		super();
	}

	/**
	 * Constructs a new response with the specified
	 * 
	 * @param token {@link String} token
	 * @param type {@link String} token type
	 * @param id {@link Long} user id
	 * @param login {@link String} user login
	 * @param roles {@link List} of {@link String} user roles
	 */
	public JwtResponse(String token, String type, Long id, String login, List<String> roles) {
		super();
		this.token = token;
		this.type = type;
		this.id = id;
		this.login = login;
		this.roles = roles;
	}


	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

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
