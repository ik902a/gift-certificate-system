package com.epam.esm.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.epam.esm.service.impl.UserDetailsServiceImpl;

/**
 * The {@code JwtTokenFilter} class represents filter for token
 * 
 * @author Ihar Klepcha
 * @see OncePerRequestFilter
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	public static Logger log = LogManager.getLogger();
	private static final String AUTHORIZATION_TYPE = "Bearer ";
	private JwtTokenUtil jwtTokenUtil;
	private UserDetailsServiceImpl userDetailsService;

	/**
	 * Constructs a new token security filter with the specified
	 * 
	 * @param jwtTokenUtil       {@link JwtTokenUtil} util class
	 * @param userDetailsService {@link UserDetailsServiceImpl} service for user
	 *                           data
	 */
	@Autowired
	public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl userDetailsService) {
		super();
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			Optional<String> jwt = parseJwt(request);
			if (jwt.isPresent() && jwtTokenUtil.validateJwtToken(jwt.get())) {
				String username = jwtTokenUtil.getUserNameFromJwtToken(jwt.get());
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.error("Cannot set user authentication: {}", e);
		}
		filterChain.doFilter(request, response);
	}

	private Optional<String> parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
		Optional<String> jwt = Optional.empty();
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(AUTHORIZATION_TYPE)) {
			jwt = Optional.of(headerAuth.replaceFirst(AUTHORIZATION_TYPE, "").trim());
		}
		return jwt;
	}
}