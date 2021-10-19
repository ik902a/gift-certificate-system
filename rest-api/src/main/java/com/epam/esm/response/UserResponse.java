package com.epam.esm.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.UserDto;

public class UserResponse extends RepresentationModel<UserResponse> {
	private long id;
    private String login;
    private List<OrderResponse> orders;
	
	public UserResponse(long id, String login, List<OrderResponse> orders) {
		super();
		this.id = id;
		this.login = login;
		this.orders = orders;
	}
	
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
}
