package hello.advanced.trace;

/**
 * 로그의 상태 정보를 나타낸다.
 */
public class TraceStatus {
    private TraceId traceId;//동기화 아이디
    private Long startTimeMs;//쓰레드 시작시간
    private String message;//쓰레드 내용

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
