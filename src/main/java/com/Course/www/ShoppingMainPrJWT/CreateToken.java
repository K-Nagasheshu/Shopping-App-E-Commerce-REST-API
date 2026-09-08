package com.Course.www.ShoppingMainPrJWT;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class CreateToken {
	
	private JwtEncoder jwtEncoder;

	public CreateToken(JwtEncoder jwtEncoder) {
		super();
		this.jwtEncoder = jwtEncoder;
	}
	
	public JWTResponse authenticate(Authentication authentication) {
		return new JWTResponse(createToken(authentication));
	}
	
	private String createToken(Authentication authentication) {
		var Claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(Instant.now())
				.expiresAt(Instant.now().plusSeconds(60 * 30))
				.subject(authentication.getName())
				.claim("scope", createScope(authentication))
				.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(Claims)).getTokenValue();
		
	}

	private Object createScope(Authentication authentication) {
		return authentication.getAuthorities().stream()
				.map(a -> a.getAuthority())
				.collect(Collectors.joining(""));
	}

}
