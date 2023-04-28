package com.testportal.authservice.helper;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testportal.authservice.dto.CredentialsDto;
import com.testportal.authservice.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {

	private Logger log = LoggerFactory.getLogger(JwtHelper.class);

	@Value("${jwt.auth.secret.key}")
	private String jwtSecretKey;

	@Autowired
	private ObjectMapper mapper;

	public String extractUsername(String token) {

		String methodname = "extractUsername()";
		log.info(methodname + " called");

		return extractClaim(token, Claims::getSubject);
	}

//	public String extractUser(String token) {
//
//		String methodname = "extractUsername()";
//		log.info(methodname + " called");
//
//		return extractClaim(token, Claims::get);
//	}

	public Date extractExpiration(String token) {

		String methodname = "extractExpiration()";
		log.info(methodname + " called");

		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

		String methodname = "extractClaim()";
		log.info(methodname + " called");

		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		String methodname = "extractAllClaims()";
		log.info(methodname + " called");

		return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {

		String methodname = "isTokenExpired()";
		log.info(methodname + " called");

		return extractExpiration(token).before(new Date());
	}

//	public String generateToken(String userName) {
//
//		String methodname = "generateToken()";
//		log.info(methodname + " called");
//
//		Map<String, Object> claims = new HashMap<>();
//		return createToken(claims, userName);
//	}

	public String generateToken(UserDto user) {

		String methodname = "generateToken()";
		log.info(methodname + " called");

		@SuppressWarnings("unchecked")
		Map<String, Object> claims = mapper.convertValue(user, Map.class);
		claims.remove("password");
		return createToken(claims, user.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {

		String methodname = "createToken()";
		log.info(methodname + " called");

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
	}

	public Boolean validateToken(String token, CredentialsDto userCred, String extractedusername) {

		String methodname = "validateToken()";
		log.info(methodname + " called");

		final String username = extractedusername != null ? extractedusername : extractUsername(token);
		// need to validate password also but currently not able to find way to get all claims in key value format
		return (username.equals(userCred.getUsername()) && !isTokenExpired(token));
	}
}
