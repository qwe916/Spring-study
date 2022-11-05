package hello.advanced.trace;

import java.util.UUID;

public class TraceId {
    private String id;//트랜잭션 id
    private int level;//트랜잭션 level

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }
    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);//생성된 UUID 앞의 8자리만 사용
    }

    //트레이스 id는 같으나 level을 다르게 해주기 위한 메소드
    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level + 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
