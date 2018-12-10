//package file.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
//import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ImportResource({"classpath:config.xml"})
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    PermissionEvaluatorImpl permissionEvaluator;
//
//    public void configureGlobal(final AuthenticationManagerBuilder auth, final UserDetailsService userDetailsService)
//            throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
//    }
//    
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//    public void configure(final WebSecurity web) {
//        final DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
//        handler.setPermissionEvaluator(permissionEvaluator);
//        web.expressionHandler(handler);
//    }
//    
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/login*", "/index*", "/shop*", "/signup*", "/product*",
//                        "/styles/**", "/js/**", "/fonts/**", "/images/**", "/plugins/**").permitAll()
//                .antMatchers("*/cart*").hasRole("USER")
//                .antMatchers("*/user_list*").hasRole("ADMIN, SYSTEM")
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/login").permitAll()
//                .failureUrl("/login")
//                .and()
//            .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .permitAll()
//                .and()
//            .headers()
//                .frameOptions().sameOrigin()
//                .and()
//                .httpBasic();
//        
//        http.csrf().disable();
//    }
//}
