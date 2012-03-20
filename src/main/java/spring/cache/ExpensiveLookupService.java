package spring.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ExpensiveLookupService {
    
    @Cacheable("default")
    public String get(String s) {
        System.out.println("fetching value for: " + s);
        return "value for " + s;
    }
    
    @CacheEvict("default")
    public void evict(String s) {
        
    }
}
