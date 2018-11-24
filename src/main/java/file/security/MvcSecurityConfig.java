package file.security;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcSecurityConfig implements WebMvcConfigurer {
    
    public void interceptView(final ViewControllerRegistry vcr) {
        
        vcr.addRedirectViewController("/logout", "/");
        vcr.addViewController("/login").setViewName("login");
    }
    
}
