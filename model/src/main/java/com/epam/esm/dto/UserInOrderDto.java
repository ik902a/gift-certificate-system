package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;

/**
 * The {@code UserInOrderDto} class is implementation of pattern DTO for transmission user
 * entity in order between service and controller
 *
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class UserInOrderDto {//TODO Depricate
	private long id;
    private String login;
    
    /**
	 * Constructs a new User in order DTO
	 */
	public UserInOrderDto() {
		super();
	}
	
	/**
	 * Constructs a new user in order DTO with the specified
	 * 
	 * @param id is user id
	 * @param login {@link String} login
	 */
	public UserInOrderDto(long id, String login) {
		super();
		this.id = id;
		this.login = login;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInOrderDto other = (UserInOrderDto) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nUserInOrderDto{ id=").append(id);
		sb.append(", login=").append(login).append(" }");
		return sb.toString();
	}
}
