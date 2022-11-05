package hello.advanced.trace;

import java.util.UUID;

public class TraceId {
    private String id;//트랜잭션 id
    private int level;//트랜잭션 level

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);//생성된 UUID 앞의 8자리만 사용
    }
}
