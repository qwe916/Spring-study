package hello.advanced.trace.templete.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 * Context는 변하지 않는 템플릿 역할을 하는 코드이다.
 * strategy를 통하여 일부 전략을 변경한다. (구현체 주입으로 전략 변경)
 * 새로운 전략으로 바꾸어도 Context는 변경할 필요가 없다.
 * 이 전략 패턴은 스프링의 의존관계 주입에 사용하는 디자인 패턴이다.
 */
@Slf4j
public class ContextV1 {
    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call();//위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
