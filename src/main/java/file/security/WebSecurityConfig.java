package file.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PermissionEvaluatorImpl permissionEvaluator;

    public void configureCommon(final AuthenticationManagerBuilder auth, final UserDetailsService userDetailsService)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
    }
    
    public void configure(final WebSecurity web) {
        final DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator);
        web.expressionHandler(handler);
    }
    
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login*", "/index*", "/shop*", "/signup*", "/product*",
                        "/styles/**", "/js/**", "/fonts/**", "/images/**", "/plugins/**").permitAll()
                .antMatchers("/cart*").hasRole("ROLE_USER")
                .antMatchers("/users*").hasRole("ROLE_ADMIN, ROLE_SYSTEM")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
            .headers()
                .frameOptions().sameOrigin();
        
        http.csrf().disable();
    }
}
