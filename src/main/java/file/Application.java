package file;

import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import file.entity.Role;
import file.entity.User;
import file.service.UserService;

@ComponentScan
public class Application {

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//        XmlWebApplicationContext context = new XmlWebApplicationContext();
//        context.setConfigLocation("/WEB-INF/mvc-servlet.xml");
//
//        ServletRegistration.Dynamic mvc = servletContext.addServlet("mvc", new DispatcherServlet(context));
//        mvc.setLoadOnStartup(1);
//        mvc.addMapping("/storefront");
//
//    }

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        final ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config.xml");
        ctx.start();
        
        final UserService userService = ctx.getBean(UserService.class);
        
        for (Role role : userService.getRoles()) {
            log.info(role.getName());
        }

        for (User user : userService.getAll()) {
            log.info(user.getName());
        }

        User u = new User();
        u.setName("user_" + new Random().nextInt());
        u.setPassword("user_password");
        u.setMail("user_mail");
        u.setConfirmToken("true");
        userService.addUser(u);

        log.info("id:" + u.getId());
        System.out.println("HELLO_WORLD!!!!!");

        for (User user : userService.getAll()) {
            log.info("user: {}", user.getName());
            if (user.getRoles() != null) {
                for (Role role : user.getRoles()) {
                    log.info("role: {}", role.getName());
                }
            }
        }

        ctx.stop();
        ctx.close();
    }

}
