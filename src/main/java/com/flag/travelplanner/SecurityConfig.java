package com.flag.travelplanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?");
        // authentication manager (see below)

    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.csrf().disable()
                .formLogin()
                //.loginPage("/users/login")
                 .loginProcessingUrl("/users/login")
                .failureForwardUrl("/users/login?error=true");
        http
                .authorizeRequests()
                .antMatchers("/routes", "/routes/*")
                    .hasAuthority("ROLE_USER")
                .anyRequest().permitAll();

        // http builder configurations for authorize requests and form login (see below)
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
   // public PasswordEncoder passwordEncoder() {
   //     return new BCryptPasswordEncoder();
   // }
}

