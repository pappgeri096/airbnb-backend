package com.codecool.airbnbmanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        UserDetails userDetails = User
                .withDefaultPasswordEncoder()
                .username("user")
                .password("pass").roles("USER").build();

        UserDetails adminDetails = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("passs").roles("USER", "ADMIN").build();


        return new InMemoryUserDetailsManager(userDetails, adminDetails);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity // todo: in practice, it won't look like this. see: https://auth0.com quickstart to integrate google/facebook/etc
                .authorizeRequests() // specifies the kind of authorization belonging to certain paths and roles...
                .antMatchers("/", "/login")
                .permitAll() // these paths are available without logging in.
                .antMatchers("/secret-method")
                .hasRole("ADMIN") // secret-method path is only accessible with ADMIN role
                .anyRequest() // all other requests is served only if...
                .authenticated() // the user is authenticated (being logged in) in any role
                .and()
                .formLogin() // display login form
                .and()
                .logout() // logout with invalidating http session
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
                .csrf() // disable cross-site request forgery protection. do not disable it in non test circumstances
                .disable(); // we need to do this to be able to logout
    }

}
