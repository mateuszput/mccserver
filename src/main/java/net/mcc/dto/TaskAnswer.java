package net.mcc.dto;

public class TaskAnswer {
    private Long taskID;
    private String answer;

    public TaskAnswer() {
    }

    public TaskAnswer(Long taskID, String answer) {
        this.taskID = taskID;
        this.answer = answer;
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
