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
	byte[] secret = Base64.getDecoder().decode("uidupQNPG1sBZZNA34U9eTgECs6BVfhAIOZtWi/BR0Y=");
	
	/**
	 * @return String with JWT
	 */
	public String encodeJWToken(String username) {
		Instant now = Instant.now();
		
		String jwt = Jwts.builder()
			.setSubject(username)
			.setAudience("DigitaleFactuur")
			.claim("1d20", new Random().nextInt(20) + 1)
			.setIssuedAt(Date.from(now))
			.setExpiration(Date.from(now.plus(1, ChronoUnit.MINUTES)))
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
//				.requireAudience("school")	
				.setSigningKey(Keys.hmacShaKeyFor(this.secret))
				.parseClaimsJws(jwtoken);
			
			validation = true;
	    } catch (Exception e) {
		      System.out.println("The JWT has an incorrect secret key. The request to the API is restricted.");
	    }
		
		return validation;
	}
}
