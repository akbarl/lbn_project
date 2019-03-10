package com.lbn.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {

	public static final String CLIENT_ID = "client-lbl-web-ui";
	public static final String SECRET_KEY = "secret-lbl-web-ui";
	public static final String[] GRANT_TYPES = {"password", "authorization_code", "refresh_token"};
	public static final String READ_SCOPE = "read";
	public static final String WRITE_SCOPE = "write";
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DataSource m_dataSource;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(CLIENT_ID).secret(new BCryptPasswordEncoder().encode(SECRET_KEY))
				.authorizedGrantTypes(GRANT_TYPES).scopes(READ_SCOPE);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
	}

	@Bean
	public TokenStore tokenStore() {
		//return new JdbcTokenStore(dataSource());
		return new InMemoryTokenStore();
	}
	
	public DataSource dataSource() {
		return m_dataSource;
	}
}
