package spring;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import spring.profiles.Database;

public class BeanProfileTest extends SpringContextTestSupport {
    private static final String PROFILES_ACTIVE_PROPERTY = "spring.profiles.active";

    @Test
    public void testBeanProfiles() {
        System.setProperty(PROFILES_ACTIVE_PROPERTY, "dev");
        ApplicationContext context = createApplicationContext();
        Map<String, Database> beans = context.getBeansOfType(Database.class);
        System.out.println(beans);
        System.out.println(beans.values().iterator().next().getName());
        
        // PROD
        System.setProperty(PROFILES_ACTIVE_PROPERTY, "prod");
        context = createApplicationContext();
        beans = context.getBeansOfType(Database.class);
        System.out.println(beans);
        System.out.println(beans.values().iterator().next().getName());
    }
}
