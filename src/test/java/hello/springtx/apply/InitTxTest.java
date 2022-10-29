package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import
        org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;

@SpringBootTest
public class InitTxTest {
    @Autowired
    Hello hello;

    @Test
    void go() {
        //초기화 코드는 스프링이 초기화 시점에 호출한다.
    }

    @TestConfiguration
    static class InitTxTestConfig {
        @Bean
        Hello hello() {
            return new Hello();
        }
    }

    @Slf4j
    static class Hello {
        /**
         * @PostConstruct
         * 위 어노테이션을 사용하면 트랜잭션을 획득할 수 없다.
         * 왜냐하면 초기화 코드가 먼저 실행되고 트랜잭션 AOP가 적용되기 때문에 초기화 시점에는 트랜잭션을 획들할 수 없다.
         */
        @PostConstruct
        @Transactional
        public void initV1() {
            boolean isActive =
                    TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init @PostConstruct tx active={}", isActive);
        }

        /**
         * @EventListener
         * 스프링 컨테이너가 완전히 생성된 후에 이벤트가 붙은 메서드를 호출해준다.
         * ApplicationReadyEvent
         */
        @EventListener(value = ApplicationReadyEvent.class)
        @Transactional
        public void init2() {
            boolean isActive =
                    TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init ApplicationReadyEvent tx active={}",
                    isActive);
        }
    }
}