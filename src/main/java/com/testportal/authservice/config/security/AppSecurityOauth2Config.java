//package com.testportal.authservice.config.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//import com.testportal.authservice.constants.AppConstants;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Configuration
//@Slf4j
//public class AppSecurityOauth2Config extends AuthorizationServerConfigurerAdapter {
//
//	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
//	private String clientId;
//	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
//	private String clientSecret;
//
//	@Autowired
//	@Qualifier("authenticationManagerBean")
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer());
//	}
//
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//	}
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write").authorizedGrantTypes("password", "refresh_token")
//				.accessTokenValiditySeconds(36000).refreshTokenValiditySeconds(20000);
//
//	}
//
//	@Bean
//	JwtAccessTokenConverter tokenEnhancer() {
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setSigningKey(AppConstants.PVT_KEY.value);
//		converter.setVerifierKey(AppConstants.PUBLIC_KEY.value);
//		return converter;
//	}
//
//	@Bean
//	JwtTokenStore tokenStore() {
//		JwtTokenStore jwts = new JwtTokenStore(tokenEnhancer());
//		return jwts;
//	}
//}
