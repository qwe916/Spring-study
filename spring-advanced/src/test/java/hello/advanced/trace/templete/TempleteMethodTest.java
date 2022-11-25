package hello.advanced.trace.templete;

import hello.advanced.trace.templete.code.AbstractTemplate;
import hello.advanced.trace.templete.code.SubClassLogic1;
import hello.advanced.trace.templete.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TempleteMethodTest {
    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    /**
     * 템플릿 메서드 패턴 사용
     */
    @Test
    void TemplateMethodV1() {
        AbstractTemplate abstractTemplate1 = new SubClassLogic1();
        abstractTemplate1.execute();
        AbstractTemplate abstractTemplate2 = new SubClassLogic2();
        abstractTemplate2.execute();
    }

    @Test
    void TemplateMethodV2() {
        AbstractTemplate abstractTemplate1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 1");
            }
        };
        abstractTemplate1.execute();
        AbstractTemplate abstractTemplate2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 2");
            }
        };
        abstractTemplate2.execute();
    }
    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
