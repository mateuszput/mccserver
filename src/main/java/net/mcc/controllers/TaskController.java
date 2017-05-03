package net.mcc.controllers;

import net.mcc.dto.Task;
import net.mcc.dto.TaskAnswer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TaskController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(path = "/tasks", method = RequestMethod.POST)
    public TaskAnswer runTask(@RequestBody Task taskData) {
        return new TaskAnswer(counter.incrementAndGet());
    }
}