package net.mcc.dto;

public class TaskAnswer {
    private Long taskID;

    public TaskAnswer(Long taskID) {
        this.taskID = taskID;
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }
}
