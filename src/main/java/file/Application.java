package file;

import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import file.entity.Role;
import file.entity.User;
import file.security.UserDetailsServiceImpl;
import file.service.UserService;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.util.SpringBootFatJar;

// @ImportResource(locations = { "classpath:config.xml",
// "classpath:security-context.xml" })
@SpringBootApplication
@ComponentScan(basePackages = { "file.mvc", "file.service.impl", "file.dao.jdbc", "file.security", "file.config" })
public class Application extends SpringBootServletInitializer {

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//        XmlWebApplicationContext context = new XmlWebApplicationContext();
//        context.setConfigLocation("/WEB-INF/mvc-servlet.xml");
//
//        ServletRegistration.Dynamic mvc = servletContext.addServlet("mvc", new DispatcherServlet(context));
//        mvc.setLoadOnStartup(1);
//        mvc.addMapping("/*");
//    }

    // @Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder
    // application) {
    // return application.sources(SpringBootServletInitializer.class);
    // }

    final String changelog = "classpath:databaseChangeLog.xml";
    final String contexts = "derby";
    
//    @Bean(destroyMethod = "close")
//    public DataSourceConnectionFactory dataSource(Environment env) {
//        final BasicDataSource conf = new BasicDataSource();
//        conf.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
//        conf.setUrl("jdbc:derby:.derby;create=true");
//        conf.setMaxTotal(100);
//        conf.setMaxIdle(20);
//        return new DataSourceConnectionFactory(conf);
//
//    }
    
    @Bean("liquibase")
    @ConfigurationProperties("spring.datasource")
    public SpringLiquibase springLiquibase(@Autowired final DataSource dataSource) {
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changelog);
        liquibase.setContexts(contexts);
        return liquibase;
    }

    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(Application.class);
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

}
