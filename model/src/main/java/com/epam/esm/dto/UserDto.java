package com.epam.esm.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class UserDto {
	private Long id;
	@NotBlank
    @Size(max = 45)
    private String login;
	@Valid
	private List<OrderDto> orders;
	
    /**
	 * Constructs a new User DTO
	 */
	public UserDto() {
		super();
	}

	/**
	 * Constructs a new user DTO with the specified
	 * 
	 * @param id {@link Long} user id
	 * @param login {@link String} login
	 * @param orders {@link List} of {@link OrderForUserDto} is list orders
	 */
	public UserDto(Long id, String login, List<OrderDto> orders) {
		super();
		this.id = id;
		this.login = login;
		this.orders = orders;
	}
	
	public void addOrder(OrderDto order) {
		if (orders == null) {
			orders = new ArrayList<>();
		}
		orders.add(order);
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

	public List<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, orders);
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
		return id == other.id && Objects.equals(login, other.login) && Objects.equals(orders, other.orders);
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
