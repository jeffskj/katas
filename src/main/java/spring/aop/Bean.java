package spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Bean {
    
    @Secured
    public void doSomethingSecure() {
        System.out.println("annotated with @Secure");
    }
    
    public void doSomethingUnsecure() {
        System.out.println("not annotated with @Secure");
    }
}
