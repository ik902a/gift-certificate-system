package com.epam.esm.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The {@code UserDto} class is implementation of pattern DTO for transmission user entity 
 * between service and controller
 *
 * @author Ihar Klepcha
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDto {
	private Long id;
	@NotBlank
    @Size(max = 45)
    private String login;
	
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
	 */
	public UserDto(Long id, String login) {
		super();
		this.id = id;
		this.login = login;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, login);
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
		return Objects.equals(id, other.id) && Objects.equals(login, other.login);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nUserDTO{ id=").append(id);
		sb.append(", login=").append(login).append(" }");
		return sb.toString();
	}
}
