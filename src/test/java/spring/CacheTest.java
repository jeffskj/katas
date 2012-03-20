package spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import spring.cache.ExpensiveLookupService;

public class CacheTest extends SpringContextTestSupport {

    @Test
    public void testCaching() throws InterruptedException {
        ApplicationContext ctx = createApplicationContext();
        ExpensiveLookupService lookupService = ctx.getBean(ExpensiveLookupService.class);
        
        System.out.println(lookupService.get("foo"));        
        System.out.println(lookupService.get("foo"));
        System.out.println(lookupService.get("foo"));
        
        lookupService.evict("foo");
        System.out.println(lookupService.get("foo"));        
        System.out.println(lookupService.get("foo"));
    }
}
