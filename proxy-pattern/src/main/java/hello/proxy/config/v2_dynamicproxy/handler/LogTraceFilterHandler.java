package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceFilterHandler implements InvocationHandler {
    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;
    public LogTraceFilterHandler(Object target, LogTrace logTrace, String...
            patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws
            Throwable {
        //메서드 이름 필터 : 특정 메서드 이름이 매칭 되는 경우에만 로직 실행
        String methodName = method.getName();//메서드 이름 가져오기
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {//패턴에 있는 메소드와 일치치하지 않으면
            return method.invoke(target, args);//메소드 실행
        }
        TraceStatus status = null;
        try {
            //메소드를 선언한 클래스의 이름 + 메소드 이름+()
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);
            //로직 호출
            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
