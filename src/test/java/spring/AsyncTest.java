package spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import spring.async.FireAndForgetMessageService;

public class AsyncTest extends SpringContextTestSupport {

    @Test
    public void testAsync() throws InterruptedException {
        ApplicationContext ctx = createApplicationContext();
        FireAndForgetMessageService messageService = ctx.getBean(FireAndForgetMessageService.class);
        for (int i = 0; i < 20; i++) {
            messageService.fire("Hello");
        }
        Thread.sleep(1000);
    }
}
