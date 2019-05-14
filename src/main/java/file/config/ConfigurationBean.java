//package file.config;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.apache.commons.dbcp2.DataSourceConnectionFactory;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//@Configuration
//@EnableTransactionManagement
//@EnableWebMvc
//public class ConfigurationBean {
//    
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
//}
