package com.epam.esm.dto;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.esm.entity.User;

/**
 * The {@code UserDetailsImpl} class presents user data for authentication and access
 *
 * @author Ihar Klepcha
 * @see UserDetails
 */
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * Constructs a new user data
	 * 
	 * @param id {@link Long} user id
	 * @param username {@link String} username
	 * @param password {@link String} password
	 * @param authorities {@link Collection} of {@link GrantedAuthority} list of authorities
	 */
	public UserDetailsImpl(Long id, String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	/**
	 * Builds a new entity with user data 
	 * 
	 * @param user {@link User}  entity
	 * @return {@link UserDetailsImpl} data for authentication and access
	 */
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().toString()));
		return new UserDetailsImpl(
				user.getId(), 
				user.getLogin(), 
				user.getPassword(), 
				authorities);
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorities, id, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailsImpl other = (UserDetailsImpl) obj;
		return Objects.equals(authorities, other.authorities) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("UserDetails{ id=").append(id);
		sb.append(", login=").append(username);
		sb.append(", password=").append(password);
        sb.append(", authorities=").append(authorities).append(" }");
		return sb.toString();
	}
}
