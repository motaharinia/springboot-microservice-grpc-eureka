package ir.micser.config.oauth2.server;


import ir.micser.login.business.service.authentication.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity(debug = true)
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private String tokenUrl = "/oauth/token";

    // It is the interface responsible to be the bridge between your data source and Spring Security:
    @Autowired
    private UserDetailService userDetailService;

    @Bean
    protected AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new CustomPasswordEncoder());
    }

    private OAuth2ClientAuthenticationProcessingFilter getFilter() {
        OAuth2ClientAuthenticationProcessingFilter oauth2Filter = new OAuth2ClientAuthenticationProcessingFilter(tokenUrl);
        oauth2Filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        });
        oauth2Filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                    throws IOException, ServletException {
                super.onAuthenticationFailure(request, response, exception);
            }
        });
        return oauth2Filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and() //فعالسازی کورس برای اسپرینگ سکیوریتی
                .addFilterBefore(getFilter(), FilterSecurityInterceptor.class)
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(tokenUrl).permitAll();

    }

    //چه مسیرهایی از آدرس پروژه اصلا توسط اسپرینگ سکیوریتی مورد بررسی قرار نگیرد
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/vutility/**")
                .antMatchers("/vcommon/**")
                .antMatchers("/vehelp/**")
                .antMatchers("/veshop/**")
                .antMatchers("/vsupport/**")
                .antMatchers("/vwww/**");
    }

}
