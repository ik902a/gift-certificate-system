package com.epam.esm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The {@code User} class describes the entity user
 * 
 * @author Ihar Klepcha
 * @see Entity
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "login")
    private String login;
    
    @OneToMany(mappedBy="user")
    private List<Order> orderList;
    
	public User() {
		super();
	}

    public User(long id, String login, List<Order> orderList) {
		super();
		this.id = id;
		this.login = login;
		this.orderList = orderList;
	}

	public void addOrder(Order order) {
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        orderList.add(order);
        order.setUser(this);
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

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nUser{ id=").append(id);
		sb.append(", login=").append(login).append(" }");
		return sb.toString();
	}
}
