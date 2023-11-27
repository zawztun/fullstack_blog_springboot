package blog_rest_jpa.blog_rest_jpa.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {
	public  String generateToken(String username) {
		String jwtToken = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+ 10*60*1000))
				.signWith(SignatureAlgorithm.HS256,"secretKey")
				.compact();
		return jwtToken;
	}

	public String getUsernameFromToken(String token) throws AuthenticationCredentialsNotFoundException{
		try{
			Claims body = Jwts.parser()
					.setSigningKey("secretKey")
					.parseClaimsJws(token)
					.getBody();
			return body.getSubject();
		}
		catch (Exception e){
			throw new AuthenticationCredentialsNotFoundException("Invalid Token");
		}
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token);
			return true;
		} catch (Exception ex) {
			throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
		}
	}
}
