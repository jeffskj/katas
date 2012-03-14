package jaxb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.ParameterizedType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.junit.Test;

public class JaxbMarshallingTest {

    @Test
    public void test() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Wishlist.class);
        Marshaller marshaller = context.createMarshaller();
        
        Wishlist wl = new Wishlist();
        wl.name = "blah";
        wl.id = 1;
        wl.user = new User();
        wl.user.id = 7;
        wl.user.name = "Joe Blow";
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(wl, os);
        String out = os.toString();
        System.out.println(out);
        
        Wishlist unmarshalled = (Wishlist) context.createUnmarshaller().unmarshal(new ByteArrayInputStream(out.getBytes()));
        System.out.println(unmarshalled.user.id);
        System.out.println(unmarshalled.user.name);
    }

    static abstract class BaseIdOnlyXmlAdapter<T extends Base> extends XmlAdapter<Long, T> {
        protected T newInstance() {
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            @SuppressWarnings("unchecked")
            Class<T> type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
            try {
                return type.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        @Override
        public T unmarshal(Long v) throws Exception {
            T instance = newInstance();
            instance.setId(v);
            return instance;
        }

        @Override
        public Long marshal(T v) throws Exception {
            return v.getId();
        }
        
    }
    
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Wishlist {
        private int id;

        @XmlJavaTypeAdapter(User.IdOnlyXmlAdapter.class)
        private User user;

        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    @XmlRootElement
    static class User extends Base {
        public static final class IdOnlyXmlAdapter extends BaseIdOnlyXmlAdapter<User> {};
        
        private long id;
        private String name;

        @Override
        public long getId() {
            return id;
        }

        @Override
        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    
    static abstract class Base {
        abstract long getId();
        abstract void setId(long id);
    }
}