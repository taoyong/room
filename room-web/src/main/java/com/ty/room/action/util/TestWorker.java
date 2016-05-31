package com.ty.room.action.util;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/5/30
 * Time: 17:45
 * Description:
 */
public class TestWorker extends QuartzJobBean {


    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Service4Job.job();
    }

    public static class  Service4Job{
        public static void job(){
            System.out.println("定时任务 **** "+System.currentTimeMillis());
        }
    }
}
