package net.mcc.services;

import net.mcc.dto.IncomingTaskResult;
import net.mcc.dto.TaskAnswer;
import net.mcc.wrappers.TaskInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaskResultsService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<Long, TaskInfo> resultsReadyMap;

    public TaskResultsService() {
        resultsReadyMap = new HashMap<>();
    }

    public void addToMap(Long taskId){
        resultsReadyMap.put(taskId, new TaskInfo());
    }


    public void postResult(IncomingTaskResult taskID) {
        log.info("received result for ID: " + taskID.getId());
        log.info("received result answer: " + taskID.getAnswer());

        resultsReadyMap.get(taskID.getId()).setAnswer(taskID.getAnswer());
    }

    public TaskAnswer getResult(Long taskID) {
        TaskAnswer taskAnswer = new TaskAnswer();
        taskAnswer.setTaskID(taskID);

        TaskInfo taskInfo = resultsReadyMap.getOrDefault(taskID, null);
        if (taskInfo == null) {
            log.error("Concurrent access error");
        }

        if (taskInfo.getAnswer() != null) {
            taskAnswer.setAnswer(taskInfo.getAnswer());
        }

        return taskAnswer;
    }

}
