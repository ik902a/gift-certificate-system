package com.epam.esm.response;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.Role;

/**
 * The {@code UserResponse} class describes the response user
 * 
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class UserResponse extends RepresentationModel<UserResponse> {
	private long id;
    private String login;
    private Role role;
	
    /**
	 * Constructs a new response
	 */
	public UserResponse() {
		super();
	}

	/**
	 * Constructs a new response with the specified
	 * 
	 * @param id is user id
	 * @param login {@link String} login
	 */
	public UserResponse(long id, String login, Role role) {
		super();
		this.id = id;
		this.login = login;
		this.role = role;
	}
	
	/**
	 * Builds a new response 
	 * 
	 * @param user {@link UserDto}  entity
	 * @return {@link UserResponse} response
	 */
    public static UserResponse valueOf(UserDto user) {
    	return new UserResponse(user.getId(), user.getLogin(), user.getRole());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, login, role);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserResponse other = (UserResponse) obj;
		return id == other.id && Objects.equals(login, other.login) && role == other.role;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nUser{ id=").append(id);
		sb.append(", login=").append(login);
		sb.append(", role=").append(role).append(" }");
		return sb.toString();
	}
}
