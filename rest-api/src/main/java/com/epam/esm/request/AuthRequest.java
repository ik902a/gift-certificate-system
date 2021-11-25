package com.epam.esm.request;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The {@code AuthRequest} class describes data for authentication
 *
 * @author Ihar Klepcha
 */
public class AuthRequest {
	@NotBlank
	@Size(max = 45)
	private String username;
	@NotBlank
	@Size(min = 6, max = 10)
	private String password;

	/**
	 * Constructs an authentication request
	 */
	public AuthRequest() {
		super();
	}

	/**
	 * Constructs an authentication request with the specified
	 * 
	 * @param username {@link String} user name
	 * @param password {@link String} password
	 */
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthRequest other = (AuthRequest) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Authentication data{ name=").append(username).append(" }");
		return sb.toString();
	}
}
