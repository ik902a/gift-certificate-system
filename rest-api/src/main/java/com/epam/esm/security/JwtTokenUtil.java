package com.epam.esm.security;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.epam.esm.dto.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * The {@code JwtTokenUtil} class contains operations with JWT
 * 
 * @author Ihar Klepcha
 */
@Component
public class JwtTokenUtil {
	public static Logger log = LogManager.getLogger();
	private String jwtSecret;
	private long jwtExpirationMs;

	/**
	 * Constructs a new token util with the specified
	 * 
	 * @param jwtSecret       {@link String} key to secret
	 * @param jwtExpirationMs is key to expiration
	 */
	@Autowired
	public JwtTokenUtil(@Value("${jwt.secret}") String jwtSecret, @Value("${jwt.expirationMs}") long jwtExpirationMs) {
		super();
		this.jwtSecret = jwtSecret;
		this.jwtExpirationMs = jwtExpirationMs;
	}

	/**
	 * Generates JSON web token a new response with the specified
	 * 
	 * @param authentication {@link Authentication} represents the token
	 * @return {@link String} token
	 */
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	/**
	 * Gets username from token
	 * 
	 * @param token {@link String} token
	 * @return {@link String} name
	 */
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	/**
	 * Validates token
	 * 
	 * @param authToken {@link String} token
	 * @return true if is validated
	 */
	public boolean validateJwtToken(String authToken) {
		boolean isValid = false;
		try {
			Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(authToken);
			isValid = true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}
		return isValid;
	}
}
