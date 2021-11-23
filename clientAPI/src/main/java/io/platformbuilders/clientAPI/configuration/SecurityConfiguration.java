package io.platformbuilders.clientAPI.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/clients").hasAuthority("LIST_CLIENT")
				.antMatchers(HttpMethod.POST, "/api/v1/clients").hasAuthority("CREATE_CLIENT")
				.antMatchers(HttpMethod.PUT, "/api/v1/clients/*").hasAuthority("UPDATE_CLIENT")
				.antMatchers(HttpMethod.DELETE, "/api/v1/clients/*").hasAuthority("DELETE_CLIENT").anyRequest()
				.authenticated().and().oauth2ResourceServer().jwt()
				.jwtAuthenticationConverter(getJwtAuthenticationConverter());
	}

	JwtAuthenticationConverter getJwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
		converter.setAuthoritiesClaimName("authorizations");
		converter.setAuthorityPrefix("");
		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
		return authenticationConverter;
	}
}