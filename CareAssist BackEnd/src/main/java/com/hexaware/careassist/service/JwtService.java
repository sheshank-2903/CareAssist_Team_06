package com.hexaware.careassist.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 09-02-2024
Description : implementation of JwtService
*/

@Service
public class JwtService {
	private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

	 public static final String SECRET = "cG9zaXRpb25iYXNlYmFsbHNwcmVhZG51bWVyYWxjdXJpb3VzaHVudGFtbGVhdmluZ20=";
	
		public String generateToken(String email,String role) { //change - 3
	        logger.info("Generating token for email: {}", email);

			Map<String,Object>  claims = new HashMap<>();
			
			return createToken(claims, email,role);
			
		}
		
		public String createToken(Map<String,Object> claims , String email,String role) { //change 2
			
	        logger.info("Creating token for email: {}", email);

			return Jwts.builder()
					   .setClaims(claims)
					   .claim("role", role) //change - 1
					   .setSubject(email)
					   .setIssuedAt(new Date(System.currentTimeMillis()))
					   .setExpiration(new Date(System.currentTimeMillis()+1000*60*120))
					   .signWith(getSignKey() , SignatureAlgorithm.HS256).compact();
					   
					   
		}
		
		 private Key getSignKey() {
		        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
		        return Keys.hmacShaKeyFor(keyBytes);
		    }
		 
		 
		 
		 
		 
		  public String extractEmail(String token) {
		        logger.info("Extracting email from token");

		        return extractClaim(token, Claims::getSubject);
		    }

		    public Date extractExpiration(String token) {
		        logger.info("Extracting expiration from token");

		        return extractClaim(token, Claims::getExpiration);
		    }

		    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		        final Claims claims = extractAllClaims(token);
		        return claimsResolver.apply(claims);
		    }

		    private Claims extractAllClaims(String token) {
		        logger.info("Extracting all claims from token");

		        return Jwts
		                .parserBuilder()
		                .setSigningKey(getSignKey())
		                .build()
		                .parseClaimsJws(token)
		                .getBody();
		    }

		    private Boolean isTokenExpired(String token) {
		        logger.info("Checking if token is expired");

		        return extractExpiration(token).before(new Date());
		    }

		    public Boolean validateToken(String token, UserDetails userDetails) {
		        final String email = extractEmail(token);
		        logger.info("Validating token for email: {}", email);

		        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
		    }

}
