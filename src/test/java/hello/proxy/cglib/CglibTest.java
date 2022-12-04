package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {
    @Test
     void cglib() {
        ConcreteService target = new ConcreteService();//인터페이스가 없는 클래스
        Enhancer enhancer = new Enhancer();//CGLIB를 만드는 클래스
        enhancer.setSuperclass(ConcreteService.class);//구체 클래스를 기반으로 set
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService)enhancer.create();//동적 프록시 생성
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.call();
    }
}
