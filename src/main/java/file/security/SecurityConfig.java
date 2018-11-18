package file.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    DataSource dataSource;
    
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
                "SELECT user_name, user_password FROM user WHERE user_name = ?").authoritiesByUsernameQuery(
                        "SELECT user_id, role_id FROM user_role WHERE user_id = ?");
    }
        
        protected void configure(HttpSecurity http) throws Exception {
            // TODO fix stuff
            http.authorizeRequests()
            .antMatchers("/login**").access("permitAll").and()
            .formLogin().loginPage("/login").failureUrl("/login?error")
            .usernameParameter("user_name").passwordParameter("user_password")
            .and().exceptionHandling().accessDeniedPage("/403")
            .and().csrf();
        }
    }

