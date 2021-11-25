package com.epam.esm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.epam.esm.security.AuthEntryPointJwt;
import com.epam.esm.security.JwtTokenFilter;

/**
 * The {@code SecurityConfiguration} class contains spring security
 * configuration
 * 
 * @author Ihar Klepcha
 * @see WebSecurityConfigurerAdapter
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	private AuthEntryPointJwt unauthorizedHandler;
	private JwtTokenFilter jwtTokenFilter;

	/**
	 * Constructs a security configuration
	 * 
	 * @param userDetailsService  {@link UserDetailsService} service for searching
	 *                            user data
	 * @param unauthorizedHandler {@link AuthEntryPointJwt} for generate exception
	 *                            response
	 * @param jwtTokenFilter      {@link JwtTokenFilter} security filter
	 */
	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService, AuthEntryPointJwt unauthorizedHandler,
			JwtTokenFilter jwtTokenFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
		this.jwtTokenFilter = jwtTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/gift-certificates/**").permitAll()
				.antMatchers("/auth/login", "/auth/signup").permitAll()
				.anyRequest().authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
			.and()
			.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
