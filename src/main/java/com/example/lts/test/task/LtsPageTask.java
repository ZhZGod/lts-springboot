package com.example.lts.test.task;

import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LtsPageTask implements JobRunner {

    public static final String LTS_PAGE_TASK_TYPE = "lts_page_task";

    @Override
    public Result run(JobContext jobContext) throws Throwable {
        Job job = jobContext.getJob();
        log.info("【LtsPageTask】,message={}", job.getParam("message"));
        Result result = new Result();
        result.setMsg(LTS_PAGE_TASK_TYPE + "执行完成");
        return result;
    }
}
