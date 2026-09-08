package com.Course.www.ShoppingMainPrJWT;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.http.HttpMethod;
import jakarta.websocket.Session;

@Configuration
public class JWTBasicAuth {
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/register").permitAll()
            	.requestMatchers("/auth").permitAll()
                .requestMatchers(HttpMethod.GET, "/getallproducts").permitAll()
                .requestMatchers(HttpMethod.GET,"/product/{productid}").permitAll()
                .requestMatchers(HttpMethod.GET,"/category").permitAll()
                .requestMatchers(HttpMethod.GET,"/category/{categoryid}").permitAll()
                
                
                .anyRequest().authenticated()
            )
          //http.httpBasic();
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        return http.build();
    }
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(
	        UserDetailsService userDetailsService) {

	    DaoAuthenticationProvider provider =
	            new DaoAuthenticationProvider();

	    provider.setUserDetailsService(userDetailsService);
	    provider.setPasswordEncoder(encoder());

	    return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager( 
		AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public KeyPair keypair() {
		try {
			var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			return keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	@Bean
	public RSAKey rsaKey(KeyPair keypair) {
		return new RSAKey
				.Builder((RSAPublicKey)keypair.getPublic())
				.privateKey(keypair.getPrivate())
				.keyID(UUID.randomUUID().toString())
				.build();
	}
	
	@Bean
	public JWKSource jwkSource(RSAKey rsaKey) {
		var jwkSet = new JWKSet(rsaKey);
		var jwkSource = new JWKSource() {

			@Override
			public List get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
				return jwkSelector.select(jwkSet);
			}
		};
		return jwkSource;
	}
	@Bean
	public JwtDecoder jwtdecoder(RSAKey rsaKey) throws JOSEException  {
		return NimbusJwtDecoder
				.withPublicKey(rsaKey.toRSAPublicKey())
				.build();
	}
	@Bean
	public JwtEncoder jwtencoder(JWKSource<SecurityContext>jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}
}


