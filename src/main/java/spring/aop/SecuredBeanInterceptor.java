package spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

@Component
public class SecuredBeanInterceptor extends DefaultPointcutAdvisor implements MethodBeforeAdvice {
    private static final long serialVersionUID = 4972898267273974255L;

    public SecuredBeanInterceptor() {
        setPointcut(new Pointcut());
        setAdvice(this);
    }
    
    private static class Pointcut extends StaticMethodMatcherPointcut {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return method.isAnnotationPresent(Secured.class);
        }
    }


    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if (!Boolean.getBoolean("allow.secured")) {
            throw new RuntimeException("you're not allowed to call this method");
        }
    }
    
}
