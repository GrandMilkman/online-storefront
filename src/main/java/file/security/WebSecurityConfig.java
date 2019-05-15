package file.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth, final UserDetailsService userDetailsService) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login**", "/index**",
                        "/sign_up**", "/fonts/**", "/images/**",
                        "/js/**", "/plugins/**", "styles/**", "/resources/**").permitAll()
                .antMatchers("/user_list**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/checkout**").authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("mail")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
            .headers()
                .frameOptions().sameOrigin();
    }
}
