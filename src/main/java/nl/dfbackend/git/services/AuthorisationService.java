package nl.dfbackend.git.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/** 
 * @author Oussama Fahchouch
 */
public class AuthorisationService {
	//deze kan nog uit de code en in een map voor de beveiliging
	byte[] secret = Base64.getDecoder().decode("uidupQNPG1sBZZNA34U9eTgECs6BVfhAIOZtWi/BR0Y=");
	
	/**
	 * @return String with JWT
	 */
	public String encodeJWToken(String username) {
		Instant now = Instant.now();
		
		String jwt = Jwts.builder()
			.setSubject("Rittenregistratie")
			.setAudience("DigitaleFactuur")
			.claim("username", username)
			.setIssuedAt(Date.from(now))
			.setExpiration(Date.from(now.plus(60, ChronoUnit.MINUTES)))
			.signWith(Keys.hmacShaKeyFor(this.secret))
			.compact();
		
		return jwt;
	}
	
	/**
	 * @param jwtoken
	 * @return boolean with validated jwt or not
	 */
	public boolean decodeJWToken(String jwtoken) {
		boolean validation = false;
		
		try {
			Jws<Claims> result = Jwts.parser()	
				.setSigningKey(Keys.hmacShaKeyFor(this.secret))
				.parseClaimsJws(jwtoken);
			
			validation = true;
	    } catch (Exception e) {
		      System.out.println("The JWT has an incorrect secret key. The request to the API is restricted.");
	    }
		
		return validation;
	}
	
	/**
	 * @param jwtoken
	 * @return String with username from jwtoken
	 */
	public String retrieveUsernameFromJWToken(String jwtoken) {
		String username = "";
		
		Jws<Claims> result = Jwts.parser()	
				.setSigningKey(Keys.hmacShaKeyFor(this.secret))
				.parseClaimsJws(jwtoken);
			
		username = result.getBody().get("username").toString();
		
		return username;
	}
}
