package net.mcc.services;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaskResultsService {
    private Map<Long, Boolean> resultsReadyMap;

    public TaskResultsService(){
        resultsReadyMap = new HashMap<>();
    }

    public void postResult(Long taskID){
        resultsReadyMap.put(taskID, true);
    }

    public Boolean getResult(Long taskID) {
        return resultsReadyMap.get(taskID);
    }

}
