package net.mcc.controllers;

import net.mcc.dto.Task;
import net.mcc.dto.TaskAnswer;
import net.mcc.services.TaskExecutorService;
import net.mcc.services.TaskResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TaskController {
    @Autowired
    TaskExecutorService taskExecutorService;
    @Autowired
    TaskResultsService taskResultsService;

    @RequestMapping(path = "/tasks", method = RequestMethod.POST)
    public TaskAnswer runTask(@RequestBody Task taskData) throws IOException {
        TaskAnswer taskAnswer = taskExecutorService.startTask(taskData);
        return taskAnswer;
    }

    //TODO: uzyc poprawnych danych zwracanych przez task
    @RequestMapping(path = "/resultFromTask1", method = RequestMethod.POST)
    public void postResult(@RequestBody Task1Result taskResult) throws IOException {

//        TaskAnswer taskAnswer = taskExecutorService.startTask(taskData);
//        return taskAnswer;
    }



    @RequestMapping(path = "/pingResult", method = RequestMethod.GET)
    public boolean pingResult(@PathVariable Long taskID) throws IOException {
        return taskResultsService.getResult(taskID);
    }

}