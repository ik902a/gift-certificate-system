package com.epam.esm.dto;

import java.util.Objects;

import com.epam.esm.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The {@code UserDto} class is implementation of pattern DTO for transmission
 * user entity between service and controller
 *
 * @author Ihar Klepcha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
	private Long id;
	private String login;
	private String password;
	private Role role;

	/**
	 * Constructs a user DTO
	 */
	public UserDto() {
		super();
	}

	/**
	 * Constructs a user DTO with the specified
	 * 
	 * @param id {@link Long} user id
	 * @param login {@link String} login
	 * @param password {@link String} password
	 * @param role {@link Role} user role
	 */
	public UserDto(Long id, String login, String password, Role role) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password) && role == other.role;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("UserDTO{ id=").append(id);
		sb.append(", login=").append(login);
		sb.append(", role=").append(role).append(" }");
		return sb.toString();
	}
}
