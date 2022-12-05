package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * MethodInterceptor Interceptor를 상속받고 Interceptor는 Advice를 상속받는다.
 */
@Slf4j
public class TimeAdvice implements MethodInterceptor {
    //target을 안넣어주어도 된다. proceed 메소드를 호출하면 target 클래스를 호출한다.
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        //proceed()는 target 클래스를 호출하고 그 결과를 받는다.
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}ms", resultTime);
        return result;
    }
}
