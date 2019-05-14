package file;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import liquibase.integration.spring.SpringLiquibase;

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
