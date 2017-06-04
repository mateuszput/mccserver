package net.mcc.dto;

public class StartTaskRequest {
    private String taskType;
    private String[] taskParams;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String[] getTaskParams() {
        return taskParams;
    }

    public void setTaskParams(String[] taskParams) {
        this.taskParams = taskParams;
    }
}
