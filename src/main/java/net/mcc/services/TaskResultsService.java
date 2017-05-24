package net.mcc.services;

import net.mcc.dto.IncomingTaskResult;
import net.mcc.dto.TaskAnswer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaskResultsService {
    private Map<Long, String> resultsReadyMap;

    public TaskResultsService() {
        resultsReadyMap = new HashMap<>();
    }

    public void postResult(IncomingTaskResult taskID) {
        resultsReadyMap.put(taskID.getId(), taskID.getAnswer());
    }

    public TaskAnswer getResult(Long taskID) {
        TaskAnswer taskAnswer = new TaskAnswer();
        taskAnswer.setTaskID(taskID);

        String taskAnswerString = resultsReadyMap.getOrDefault(taskID, null);
        if (taskAnswerString != null) {
            taskAnswer.setAnswer(taskAnswerString);
        }

        return taskAnswer;
    }

}
