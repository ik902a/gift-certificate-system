package com.epam.esm.response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.UserDto;

/**
 * The {@code UserResponse} class describes the response user
 * 
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class UserResponse extends RepresentationModel<UserResponse> {
	private long id;
    private String login;
    private List<OrderResponse> orders;
	
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
	 * @param orders {@link List} of {@link OrderResponse} is list of orders
	 */
	public UserResponse(long id, String login, List<OrderResponse> orders) {
		super();
		this.id = id;
		this.login = login;
		this.orders = orders;
	}
	
	/**
	 * Builds a new response 
	 * 
	 * @param user {@link UserDto}  entity
	 * @return {@link UserResponse} response
	 */
    public static UserResponse valueOf(UserDto user) {
    	List<OrderResponse> ordersResponse = user.getOrders()
    			.stream()
    			.map(order -> OrderResponse.valueOf(order))
    			.collect(Collectors.toList());
    	return new UserResponse(user.getId(), user.getLogin(), ordersResponse);
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

	public List<OrderResponse> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderResponse> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, login, orders);
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
		return id == other.id && Objects.equals(login, other.login) && Objects.equals(orders, other.orders);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nUser{ id=").append(id);
		sb.append(", login=").append(login).append(" }");
		return sb.toString();
	}
}
