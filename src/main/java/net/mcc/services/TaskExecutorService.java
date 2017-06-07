package net.mcc.services;

import net.mcc.dto.StartTaskAnswer;
import net.mcc.dto.StartTaskRequest;
import net.mcc.wrappers.VMConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskExecutorService {
    @Autowired
    VMConnector vmConnector;
    @Autowired
    TaskResultsService taskResultsService;

    private AtomicLong currentTaskID = new AtomicLong(0);
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<String, String> servers = new HashMap<>();
    private final Map<Long, String> idResultMap = new HashMap<>();

    TaskExecutorService() {
        // TODO: wypelnic danymi z serwera
        servers.put("VM1", "http://54.76.241.36");
    }

    public StartTaskAnswer startTask(StartTaskRequest startTaskRequestData) throws IOException {
        Long taskID = currentTaskID.incrementAndGet();

        String serverName = getRandomServer();
        String serverAddress = servers.get(serverName);
        taskResultsService.addToMap(taskID, serverName, startTaskRequestData);
        vmConnector.startTask(serverAddress, taskID, startTaskRequestData);

        return new StartTaskAnswer(taskID);
    }


    private String getRandomServer(){
        int min = 0;
        int max = 1;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        switch(randomNum) {
            default:
            case 0:
                return "VM1";
            case 1:
                // TODO: podmienic na drugi wpis w tablicy
                return "VM1";
        }

    }
}
