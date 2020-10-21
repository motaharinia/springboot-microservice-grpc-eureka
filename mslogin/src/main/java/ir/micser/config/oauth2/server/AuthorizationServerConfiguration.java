package ir.micser.config.oauth2.server;
import ir.micser.config.oauth2.custom.CustomWebResponseExceptionTranslator;
import ir.micser.config.oauth2.custom.FixIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer {


    @Autowired
    private AuthenticationManager authenticationManager;

    //جهت دسترسی به دیتابیس
    @Autowired
    public DataSource dataSource;

    //مبدل خطاهایی است که به سمت کلاینت میخواهد ارسال شود
    @Autowired
    CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;


    //-- use the JdbcTokenStore to store tokens
    //نیازمند منبع پایگاه داده است برای ذخیره توکن ها بعد از لاگین کاربران
    @Bean
    public TokenStore tokenStore() {
        JdbcTokenStore jdbcTokenStore = new JdbcTokenStore(dataSource);
        return FixIdentifier.fixTokenStore(jdbcTokenStore);
    }


    //Spring Security OAuth exposes two endpoints for checking tokens (/oauth/check_token and /oauth/token_key).
    // Those endpoints are not exposed by default (have access "denyAll()").
    //So if you want to verify the tokens with this endpoint you'll have to add this to your authorization servers' config:
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        oauthServer
                .checkTokenAccess("isAuthenticated()")
                .tokenKeyAccess("permitAll()");

    }

    //برای بدست آوردن اطلاعات کلاینت از این متد استفاده می شود.
    //اطلاعات کلاینت از دیتابیس خوانده می شود
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsService clientDetailsService= new JdbcClientDetailsService(dataSource);
        clients.withClientDetails(FixIdentifier.fixClientDetailsService(clientDetailsService));

}

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .exceptionTranslator(customWebResponseExceptionTranslator);
    }
}
