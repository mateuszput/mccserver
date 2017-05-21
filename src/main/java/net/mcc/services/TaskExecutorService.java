package net.mcc.services;

import net.mcc.dto.Task;
import net.mcc.dto.TaskAnswer;
import net.mcc.wrappers.VMConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TaskExecutorService {
    @Autowired
    VMConnector vmConnector;

    private AtomicLong currentTaskID = new AtomicLong(0);
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<String, String> servers = new HashMap<>();
    private final Map<Long, String> idResultMap = new HashMap<>();

    TaskExecutorService () {
        // TODO: wypelnic danymi z serwera
        servers.put("Type1", "192.168.0.1");
    }

    public TaskAnswer startTask(Task taskData) throws IOException {
        Long taskID = currentTaskID.incrementAndGet();

        String taskType = taskData.getTaskType();
        log.info("send rest json to server: " + servers.get(taskType));

        // TODO: start new task using vmConnector
//        vmConnector.startTask(servers.get(taskType), taskID);
        return new TaskAnswer(currentTaskID.incrementAndGet());
    }
}
