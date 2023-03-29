package com.example.appuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/register","/login")
                .permitAll()
                .antMatchers("/admin").hasAnyAuthority("MANAGER")
                .antMatchers("/index")
                .hasAnyRole("USER","MANAGER")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/dashboard")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/logout")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email as principal, password as credentials, true from employee where email = ?")
                .authoritiesByUsernameQuery("select employee_email as principal, role_name as role from employee_roles where employee_email = ?")
                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
