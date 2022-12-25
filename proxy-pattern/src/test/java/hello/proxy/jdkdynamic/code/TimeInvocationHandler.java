package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK 동적 프록시
 * 공통 처리 로직
 */
@Slf4j
public class TimeInvocationHandler implements InvocationHandler {
    private final Object target;
    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 공통 처리 로직
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);//파라미터로 args를 넘겨준다.(어떻게 될 지 모르니)
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
