package file;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config.xml");
        ctx.start();
        System.out.println("HELLO_WORLD!!!!!");
    }

}

/*TODO ADD HTML VIEW: USERS PAGE, USER PAGE (EDIT USER)
 *TODO ADMIN PAGE (ADDITIONAL BUTTONS (USERS) ON THE SAME PAGES)*/

