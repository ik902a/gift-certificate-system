package com.epam.esm.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

/**
 * The {@code UserDto} class is implementation of pattern DTO for transmission user
 * entity between service and controller
 *
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class UserDto extends RepresentationModel<UserDto> {
	private long id;
	@NotBlank
    @Size(max = 45)
    private String login;
	@Valid
	private List<OrderForUserDto> orders;
	
    /**
	 * Constructs a new User DTO
	 */
	public UserDto() {
		super();
	}

	/**
	 * Constructs a new user DTO with the specified
	 * 
	 * @param id is user id
	 * @param login {@link String} login
	 * @param orders {@link List} of {@link OrderForUserDto} is list orders
	 */
	public UserDto(long id, String login, List<OrderForUserDto> orders) {
		super();
		this.id = id;
		this.login = login;
		this.orders = orders;
	}
	
	public void addOrder(OrderForUserDto order) {
		if (orders == null) {
			orders = new ArrayList<>();
		}
		orders.add(order);
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

	public List<OrderForUserDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderForUserDto> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
		UserDto other = (UserDto) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nUserForUserDTO{ id=").append(id);
		sb.append(", login=").append(login);
		sb.append(", ").append(orders).append(" }");
		return sb.toString();
	}
}
