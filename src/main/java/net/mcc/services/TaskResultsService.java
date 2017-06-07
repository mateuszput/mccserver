package net.mcc.services;

import net.mcc.dto.IncomingTaskResult;
import net.mcc.dto.StartTaskRequest;
import net.mcc.dto.TaskAnswer;
import net.mcc.wrappers.TaskInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

@Component
public class TaskResultsService {
    private final String FILE_NAME = "./results.cvs";
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<Long, TaskInfo> resultsReadyMap;

    public TaskResultsService() throws IOException {
        resultsReadyMap = new HashMap<>();
        Files.deleteIfExists(Paths.get(FILE_NAME));
        Files.write(Paths.get(FILE_NAME), TaskInfo.getHeaders().getBytes());
    }

    public void addToMap(Long taskId, String serverName, StartTaskRequest startTaskRequestData){
        resultsReadyMap.put(taskId, new TaskInfo(serverName, taskId, startTaskRequestData.getTaskType(), startTaskRequestData.getTaskParams()));
    }


    public void postResult(IncomingTaskResult taskID) {
        log.info("received result for ID: " + taskID.getId());
        log.info("received result answer: " + taskID.getAnswer());

        resultsReadyMap.get(taskID.getId()).setAnswer(taskID.getAnswer());
    }

    public TaskAnswer getResult(Long taskID) throws IOException {
        TaskAnswer taskAnswer = new TaskAnswer();
        taskAnswer.setTaskID(taskID);

        TaskInfo taskInfo = resultsReadyMap.getOrDefault(taskID, null);
        if (taskInfo == null) {
            log.error("Concurrent access error");
        }

        if (taskInfo.getAnswer() != null) {
            taskAnswer.setAnswer(taskInfo.getAnswer());
            saveTaskToFile(taskInfo);
        }

        return taskAnswer;
    }

    private void saveTaskToFile(TaskInfo taskInfo) throws IOException {
        log.info("zapisuje informacje o zadaniu: " + taskInfo.toString());
        Files.write(Paths.get(FILE_NAME), taskInfo.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

}
