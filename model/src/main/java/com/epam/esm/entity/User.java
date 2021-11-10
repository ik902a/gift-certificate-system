package com.epam.esm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
    
	/**
	 * Constructs a user
	 */
	public User() {
		super();
	}

	/**
	 * Constructs a user with the specified
	 * 
	 * @param id {@link Long} user id
	 * @param login {@link String} login
	 * @param password {@link String} password
	 * @param role {@link Role} user role
	 * @param orders {@link List} of {@link Order} list of orders
	 */
	public User(Long id, String login, String password, Role role, List<Order> orders) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.orders = orders;
	}

	public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
        order.setUser(this);
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("User{ id=").append(id);
		sb.append(", login=").append(login);
		sb.append(", role=").append(role).append(" }");
		return sb.toString();
	}
}
