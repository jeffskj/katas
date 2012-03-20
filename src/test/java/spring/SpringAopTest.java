package spring;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import spring.aop.Bean;

public class SpringAopTest extends SpringContextTestSupport {

    @Test
    public void testInterceptor() {
        ApplicationContext context = createApplicationContext();
        
        Bean bean = context.getBean(Bean.class);
        System.setProperty("allow.secured", "true");
        
        bean.doSomethingSecure();
        bean.doSomethingUnsecure();
        
        System.setProperty("allow.secured", "false");
        
        
        try {
            bean.doSomethingSecure();
            fail("shouldn't have been allowed to call this");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("not allowed"));
        }
        
        bean.doSomethingUnsecure();
    }
}
