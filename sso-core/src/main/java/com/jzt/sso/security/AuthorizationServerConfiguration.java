package com.jzt.sso.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    public static final Logger logger = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    @Qualifier("daoAuhthenticationOauthProvider")
//    private AuthenticationProvider daoAuhthenticationOauthProvider;


    @Autowired
    private DataSource dataSource;


//    @Bean
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(jwtTokenEnhancer())
                .authenticationManager(authenticationManager);



//        endpoints.authenticationManager(authenticationManager);
//         endpoints.authenticationManager(new AuthenticationManager(){
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                // TODO Auto-generated method stub
//                return daoAuhthenticationOauthProvider.authenticate(authentication);
//            }
//
//        });
//        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer());
//        // 配置TokenServices参数
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(endpoints.getTokenStore());
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.MINUTES.toSeconds(30)); //分钟
//        endpoints.tokenServices(tokenServices);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.checkTokenAccess("isAuthenticated()");
        oauthServer.allowFormAuthenticationForClients();
//        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
//
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetails());
                     clients.inMemory()
                     .withClient("some_id")
                     .secret("some_secret")
                     .authorizedGrantTypes("authorization_code")
                     .scopes("user")
                     .autoApprove(true)
                     .and()
                     .withClient("some_id")
                     .secret("some_secret")
                     .authorizedGrantTypes("authorization_code")
                     .scopes("user")
                     .autoApprove(true);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    //    @Bean
    //    protected JwtAccessTokenConverter jwtTokenEnhancer() {
    //    KeyStoreKeyFactory keyStoreKeyFactory =
    //            new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "mySecretKey".toCharArray());
    //        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    //        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
    //        return converter;
    //    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

}

