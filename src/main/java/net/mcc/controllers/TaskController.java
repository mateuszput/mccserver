package net.mcc.controllers;

import net.mcc.dto.IncomingTaskResult;
import net.mcc.dto.StartTaskAnswer;
import net.mcc.dto.StartTaskRequest;
import net.mcc.dto.TaskAnswer;
import net.mcc.services.TaskExecutorService;
import net.mcc.services.TaskResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaskExecutorService taskExecutorService;
    @Autowired
    TaskResultsService taskResultsService;

    @RequestMapping(path = "/tasks", method = RequestMethod.POST)
    public StartTaskAnswer runTask(@RequestBody StartTaskRequest startTaskRequestData) throws IOException {
        log.info("received start task request");
        return taskExecutorService.startTask(startTaskRequestData);
    }


    @RequestMapping(path = "/returnResult", method = RequestMethod.POST)
    public void postResult(@RequestBody IncomingTaskResult taskResult) throws IOException {
        log.info("received returnResult for ID: " + taskResult.getId());
        taskResultsService.postResult(taskResult);
    }


    @RequestMapping(path = "/pingResult/{id}", method = RequestMethod.GET)
    public TaskAnswer pingResult(@PathVariable Long id) throws IOException {
        log.info("received ping result");
        return taskResultsService.getResult(id);
    }

}