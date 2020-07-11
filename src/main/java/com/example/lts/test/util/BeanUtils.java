package com.example.lts.test.util;//package com.example.utils;

import com.github.ltsopensource.tasktracker.runner.JobRunner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @author kevin
 */
public class BeanUtils {

    private static ApplicationContext applicationContext;

    public static Object getBean(Class<JobRunner> clazz) throws BeansException {
        return applicationContext.getBean(getBeanName(clazz));
    }

    public static Object getBean(String beanName) throws BeansException {
        return applicationContext.getBean(beanName);
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        BeanUtils.applicationContext = applicationContext;
    }

    public static String getBeanName(Class clazz) {
        String simpleName = clazz.getSimpleName();
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }

}
