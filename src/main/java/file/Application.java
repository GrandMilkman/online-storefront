package file;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
public class Application /*implements WebApplicationInitializer*/{
    
    /*@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("/WEB-INF/mvc-servlet.xml");
        
        ServletRegistration.Dynamic mvc = servletContext.addServlet("mvc", new DispatcherServlet(context));
        mvc.setLoadOnStartup(1);
        mvc.addMapping("/storefront");
        
    }
    */
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config.xml");
        ctx.start();
        System.out.println("HELLO_WORLD!!!!!");
    }

}



