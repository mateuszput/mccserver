package net.mcc.dto;

public class StartTaskAnswer {
    private Long taskID;

    public StartTaskAnswer(Long taskID) {
        this.taskID = taskID;
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }
}
