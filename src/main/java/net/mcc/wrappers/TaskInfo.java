package net.mcc.wrappers;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public class TaskInfo {
    private String answer;
    private Long startTaskTimestamp;
    private Long endTaskTimestamp;
    private Long firstPingTimestamp;
    private AtomicLong numberOfPings;

    public TaskInfo(){
        startTaskTimestamp = new Long(Instant.now().toEpochMilli());
        numberOfPings = new AtomicLong(0);
    }

    public void setAnswer(String answer) {
        endTaskTimestamp = new Long(Instant.now().toEpochMilli());
        this.answer = answer;
    }

    public String getAnswer() {
        numberOfPings.incrementAndGet();
        if (firstPingTimestamp == null) {
            firstPingTimestamp = new Long(Instant.now().toEpochMilli());
        }
        return answer;
    }

    public Long getStartTaskTimestamp() {
        return startTaskTimestamp;
    }

    public Long getEndTaskTimestamp() {
        return endTaskTimestamp;
    }

    public Long getFirstPingTimestamp() {
        return firstPingTimestamp;
    }

    public Long getNumberOfPings() {
        return new Long(numberOfPings.get());
    }

}
