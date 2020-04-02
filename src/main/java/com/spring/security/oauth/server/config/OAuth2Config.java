package com.spring.security.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
	
	@Autowired private UserDetailsService userDetailsService;
	@Autowired private AuthenticationManager authManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory()
		       .withClient("clientId")
		       .secret("{noop}secret")
		       .authorizedGrantTypes("refresh_token","password","client_credentials")
		       .scopes("user.read","user.write")
		       .accessTokenValiditySeconds(3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints.userDetailsService(userDetailsService);
		endpoints.authenticationManager(authManager);
		endpoints.tokenStore(tokenStore());
		endpoints.accessTokenConverter(accessTokenConverter());
		
	}
	
	/**
	 * This will change the default token generation with customized key...1234 is the key in this case.
	 * @return converter JwtAccessTokenConverter
	 */
	@Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("1234");
        //converter.setVerifierKey("1234");
       
        return converter;
    }
	
	@Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

	

}
