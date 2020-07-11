package com.example.lts.test.lts;//package com.example.lts;

import com.example.lts.test.task.LtsPageTask;
import com.example.lts.test.task.LtsTestTask;
import com.example.lts.test.util.BeanUtils;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 路由job
 */
@Slf4j
@Component
public class JobRunnerDispatcher implements JobRunner {

    public static final String TEST_LTS_TASK = "test_lts_task";

    private static final ConcurrentHashMap<String, String>
            JOB_RUNNER_MAP = new ConcurrentHashMap<String, String>();

    @PostConstruct
    public void init() {
        JOB_RUNNER_MAP.put(TEST_LTS_TASK, BeanUtils.getBeanName(LtsTestTask.class));
        JOB_RUNNER_MAP.put(LtsPageTask.LTS_PAGE_TASK_TYPE, BeanUtils.getBeanName(LtsPageTask.class));
    }

    @Override
    public Result run(JobContext jobContext) throws Throwable {
        Job job = jobContext.getJob();
        String type = job.getParam("type");
        String beanName = JOB_RUNNER_MAP.get(type);
        log.info("lts dispatch beanName:{}, job:{}", beanName, job.getExtParams());
        ApplicationContext applicationContext = BeanUtils.getApplicationContext();
        return ((JobRunner) applicationContext.getBean(beanName)).run(jobContext);
    }
}