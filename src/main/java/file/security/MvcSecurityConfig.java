package file.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(value = {"file.mvc"})
public class MvcSecurityConfig implements WebMvcConfigurer {
    
    public void interceptView(final ViewControllerRegistry vcr) {
        
        vcr.addRedirectViewController("/logout", "/");
        vcr.addViewController("/login").setViewName("login");
    }
    
}
