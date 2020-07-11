package com.example.lts.test.service;

import com.example.lts.test.lts.JobRunnerDispatcher;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.jobclient.JobClient;
import com.github.ltsopensource.jobclient.domain.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LtsService {

    @Value("${lts.tasktracker.node-group:test_trade_TaskTracker}")
    private String taskTrackerNodeGroup;

    @Autowired
    private JobClient jobClient;

    /**
     *
     * @param type 任务类型
     * @param taskId 任务唯一标识
     * @param params 参数
     * @return
     */
    public Response submitJob(String type, String taskId, Map<String, String> params, String cron) {
        Job job = new Job();
        job.setCronExpression(cron);
        params.put("type", StringUtils.isBlank(type) ? JobRunnerDispatcher.TEST_LTS_TASK : type);
        job.setTaskId(taskId);
        job.setExtParams(params);
        Response response = submitJob(job);
        return response;
    }

    public Response submitJob(Job job) {
        job.setTaskTrackerNodeGroup(taskTrackerNodeGroup);
        Response response = jobClient.submitJob(job);
        return response;
    }
}
