package com.jamilis.login.security;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserAuthenticationEntryPoint userAuthenticationEntryPoint;
  @Autowired
  private UserAuthenticationProvider userAuthenticationProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().configurationSource(request -> {
              CorsConfiguration config = new CorsConfiguration();
              config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
              config.setAllowedMethods(Collections.singletonList("*"));
              config.setAllowCredentials(true);
              config.setAllowedHeaders(Collections.singletonList("*"));
              config.setMaxAge(3600L);
              return config;
            }).and()
            .csrf().disable()
            .authorizeRequests().antMatchers(HttpMethod.POST, "/sign-up").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated().and()
            .addFilterBefore(new JwtAuthenticationFilter(userAuthenticationProvider),
                    BasicAuthenticationFilter.class)
            .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint);
  }
}
