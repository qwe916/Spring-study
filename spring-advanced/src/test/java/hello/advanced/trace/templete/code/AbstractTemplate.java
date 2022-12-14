package hello.advanced.trace.templete.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void execute() {
        /**
         * 변하는 부분은 그대로 두고 call 부분만 자식 클래스에 따라 변하게 해주는 디자인 패턴 (Template Method)
         */
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
       call();//상속
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    protected abstract void call();
}
