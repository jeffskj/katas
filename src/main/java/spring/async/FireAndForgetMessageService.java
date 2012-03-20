package spring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class FireAndForgetMessageService {
    @Async
    public void fire(String message) {
        System.out.println(Thread.currentThread().getName() + ": " + message);
    }
}
