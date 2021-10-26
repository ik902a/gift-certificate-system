package com.epam.esm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The {@code User} class describes the entity user
 * 
 * @author Ihar Klepcha
 * @see AbstractEntity
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
//    @OneToMany
//    @JoinColumn(name="user_id")
    private List<Order> orders;
    
	/**
	 * Constructs a new user
	 */
	public User() {
		super();
	}

	/**
	 * Constructs a new user with the specified
	 * 
	 * @param id is user id
	 * @param login {@link String} login
	 * @param orders {@link List} of {@link Order} is list of orders
	 */
    public User(long id, String login, List<Order> orders) {
		super();
		this.id = id;
		this.login = login;
		this.orders = orders;
	}

	public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
//        order.setUser(this);
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nUser{ id=").append(id);
		sb.append(", login=").append(login).append(" }");
		return sb.toString();
	}
}
