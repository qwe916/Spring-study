package scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUserPrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();
        assertThat(count1).isEqualTo(1);
        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        assertThat(count2).isEqualTo(1);

    }


    @Scope//싱글톤 빈이 새로운 프로토타입을 사용할 때마다 스프링 컨테이너를 새로 요청하는 방법(DL Dependency LookUp)
    static class ClientBean2 {

        @Autowired
        AnnotationConfigApplicationContext ac;
        public int logic() {
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope
    static class ClientBean {
        //private final PrototypeBean prototypeBean;
        @Autowired//지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스르 제공하는것 (ObjectFactory도 가능 ObjectProvider가 기능이 좀 더 있음)
        private Provider<PrototypeBean> prototypeBeanProvider;
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;

/*
        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }
*/
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();//찾아주는 기능만 찾아서 제공
            //PrototypeBean prototypeBean = prototypeBeanProvider.getClass(); - ObjectProvider일때 사용
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("Prototype.init " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("Prototype.destory");
        }
    }
}
