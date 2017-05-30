package net.mcc.services;

import net.mcc.dto.IncomingTaskResult;
import net.mcc.dto.TaskAnswer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TaskResultsService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<Long, String> resultsReadyMap;

    public TaskResultsService() {
        resultsReadyMap = new HashMap<>();
    }

    public void postResult(IncomingTaskResult taskID) {
        log.info("received result for ID: " + taskID.getId());
        log.info("received result answer: " + taskID.getAnswer());
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
