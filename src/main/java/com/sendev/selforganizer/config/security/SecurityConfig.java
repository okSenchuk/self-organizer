package com.sendev.selforganizer.config.security;


import com.sendev.selforganizer.filter.CsrfHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private String[] adminUrls = {"/api/admin/**"};
    private String[] userUrls = {"/api/user/**"};

    @Autowired
    private CsrfTokenRepository csrfTokenRepository;

    @Autowired
    private UserDetailsService service;

    @Autowired
    private CsrfHeaderFilter csrfHeaderFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel()
                .antMatchers("/isAuthenticated", "/user", "/login", "/logout")
                .requiresInsecure()
                .antMatchers("/**")
                .requiresSecure()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/index", "/login", "/home", "/", "/logout",
                        "/isAuthenticated", "/user").permitAll()
                .antMatchers(adminUrls).hasRole("ADMIN").antMatchers(userUrls)
                .authenticated().and()
                .addFilterAfter(csrfHeaderFilter, CsrfFilter.class).csrf()
                .ignoringAntMatchers("/isAuthenticated", "/login", "/user", "/register", "/logout")
                .csrfTokenRepository(csrfTokenRepository).and()
                .logout().logoutUrl("/logout")
                .permitAll();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
