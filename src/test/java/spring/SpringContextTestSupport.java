package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringContextTestSupport {
    protected ApplicationContext createApplicationContext() {
        return new GenericXmlApplicationContext("classpath:/spring-config*.xml");
    }
}
