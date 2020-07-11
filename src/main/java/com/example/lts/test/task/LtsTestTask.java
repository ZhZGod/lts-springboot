package com.example.lts.test.task;//package com.example.lts.task;

import com.example.lts.test.lts.JobRunnerDispatcher;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LtsTestTask implements JobRunner {

    @Override
    public Result run(JobContext jobContext) throws Throwable {
        Job job = jobContext.getJob();
        log.info("{}， 执行任务", JobRunnerDispatcher.TEST_LTS_TASK);
        String message = job.getParam("message");
        System.out.println(String.format("job中任务，message=%s", message));
        Result result = new Result();
        result.setMsg(JobRunnerDispatcher.TEST_LTS_TASK + "执行完成");
        return result;
    }
}
