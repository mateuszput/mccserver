package net.mcc.wrappers;

import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class TaskInfo {
    private Long taskId;
    private String serverName;
    private String answer;
    private String taskType;
    private String[] taskParams;
    private Long startTaskTimestamp;
    private Long endTaskTimestamp;
    private Long firstPingTimestamp;
    private AtomicLong numberOfPings;

    public static String getHeaders(){
        return "taskId,serverName,taskType,taskParams,executionTime,firstPingDelay,numberOfPings";
    }

    public String toString() {
        String taskParamsString = "";
        for(int i = 0; i < taskParams.length; i++){
            taskParamsString = taskParamsString + taskParams[i];
        }

        Long executionTime = new Long(endTaskTimestamp - startTaskTimestamp);
        Long firstPingDelay = new Long(firstPingTimestamp - endTaskTimestamp);
        return System.lineSeparator() + taskId + "," + serverName + "," + taskType + "," + taskParamsString + "," + executionTime + "," + firstPingDelay + "," + numberOfPings.get();
    }

    public TaskInfo(String serverName, Long taskId, String taskType, String[] taskParams){
        this.serverName = serverName;
        this.taskId = taskId;
        this.taskType = taskType;
        this.taskParams = taskParams;
        startTaskTimestamp = new Long(Instant.now().toEpochMilli());
        numberOfPings = new AtomicLong(0);
    }

    public void setAnswer(String answer) {
        endTaskTimestamp = new Long(Instant.now().toEpochMilli());
        this.answer = answer;
    }

    public String getServerName() {
        return serverName;
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
