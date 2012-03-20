package spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import spring.qualifiers.DataSourceUser;

public class QualifierTest extends SpringContextTestSupport {
    @Test
    public void testQualifiers() {
        ApplicationContext context = createApplicationContext();
        DataSourceUser dsu = context.getBean(DataSourceUser.class);
        dsu.printDb();
    }
}
