package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {
    private final Object target;
    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    /**
     * MethodInterceptor
     * @param obj CGLIB가 적용된 객체
     * @param method 호출된 메서드
     * @param args 메서드를 호출하면서 전달된 인수
     * @param proxy 메소드 호출에 사용
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        //실제 대상을 동적으로 호출
        Object result = proxy.invoke(target, args);
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
