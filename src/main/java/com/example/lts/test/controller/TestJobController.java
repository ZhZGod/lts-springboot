package com.example.lts.test.controller;

import com.example.lts.test.lts.JobRunnerDispatcher;
import com.example.lts.test.service.LtsService;
import com.example.lts.test.util.Response;
import com.github.ltsopensource.core.domain.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/api")
public class TestJobController {

    @Autowired
    private LtsService ltsService;

    @GetMapping("/addJob")
    public ResponseEntity<Response> testAddJob(@RequestParam(name = "id")String taskId, @RequestParam(name = "message") String message, @RequestParam(name = "cron")String cron){
        Map<String, String> params = new HashMap<>();
        params.put("message", message);
//        submitJob(String type, String taskId, Map<String, String> params)
        com.github.ltsopensource.jobclient.domain.Response response = ltsService.submitJob(JobRunnerDispatcher.TEST_LTS_TASK, taskId, params, cron);
        return ResponseEntity.ok().body(Response.success(response));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("hello");
    }
}
