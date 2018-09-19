package com.perfectmatch.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
@Configuration
@EnableWebSecurity
//@ComponentScan("com.baeldung.um.security")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class PerfectMatchSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
    
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth)
            throws Exception {

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {

        // @formatter:off
        httpSecurity.authorizeRequests().
        // antMatchers("/api/**"). // if you want a more explicit mapping here
                anyRequest().permitAll();
        // .and().httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // @formatter:on

        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

}
