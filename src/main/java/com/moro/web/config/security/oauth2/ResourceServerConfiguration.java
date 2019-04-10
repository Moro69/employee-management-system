package com.moro.web.config.security.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String AUTHORITY_USER = "USER";
    private static final String AUTHORITY_ADMIN = "ADMIN";


    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/token", "/oauth/authorize**").permitAll();

        http.authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui", "/swagger-resources",
                        "/configuration/security",
                        "/webjars/**",
                        "/swagger-resources/configuration/ui",
                        "/swagger-ui.html").permitAll();

        http.requestMatchers()
                .antMatchers("/**")
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/department**", "/employee**")
                .hasAnyAuthority(AUTHORITY_ADMIN, AUTHORITY_USER)

                .antMatchers(HttpMethod.PUT, "/user")
                .hasAnyAuthority(AUTHORITY_ADMIN, AUTHORITY_USER)

                .antMatchers(HttpMethod.POST, "/user/{userId}/photo")
                .hasAnyAuthority(AUTHORITY_ADMIN, AUTHORITY_USER)

                .antMatchers(HttpMethod.GET, "/user/{userId}/photo")
                .hasAnyAuthority(AUTHORITY_ADMIN, AUTHORITY_USER)

                .antMatchers(HttpMethod.DELETE, "/user/{userId}/photo")
                .hasAnyAuthority(AUTHORITY_ADMIN, AUTHORITY_USER)

                .antMatchers(HttpMethod.POST, "/user").anonymous()

                .antMatchers("/**").hasAuthority(AUTHORITY_ADMIN);
    }
}
