package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void StatefulServiceTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        //ThreadA: A사용자가 10000원 주문
        statefulService1.order("userA", 10000);//price = 10000
        //ThreadB: B사용자가 10000원 주문
        statefulService1.order("userB", 20000);// price를 20000원으로 바꿔버림
        //ThreadA: 사용자 A가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        //ThreadB: 사용자 B가 주문 금액 조회
        int price1 = statefulService2.getPrice();
        System.out.println("price1 = " + price1);
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice());
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}