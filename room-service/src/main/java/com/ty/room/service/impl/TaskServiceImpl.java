package com.ty.room.service.impl;

import com.ty.room.domain.enumType.TaskOperateEnum;
import com.ty.room.domain.util.LogHelper;
import com.ty.room.service.TaskService;
import org.apache.commons.collections.MapUtils;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/5/31
 * Time: 11:00
 * Description:
 */
@Service(value = "taskService")
public class TaskServiceImpl implements TaskService,ApplicationContextAware {
    @Autowired
    private Map<String, Scheduler> schedulerMap;
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public void startWorker(String jobTask, String order) {
        if(MapUtils.isEmpty(schedulerMap))
            return;
        int code = TaskOperateEnum.getCodeByOrder(order);
        if(code == TaskOperateEnum.START_TASK.code){
            startTaskSchedule(jobTask);
        }else if (code == TaskOperateEnum.START_ONECE_TASK.code){
            startOnceTask(jobTask);
        }else if(code == TaskOperateEnum.STOP_TASK.code){
            pauseTaskSchedule(jobTask);
        }
    }

    public boolean isStarted(String jobTask){
        if(MapUtils.isEmpty(schedulerMap)){
            return false;
        }
        Scheduler scheduler = schedulerMap.get(jobTask);
        if(scheduler == null){
            return false;
        }
        try {
            if(scheduler.isStarted() && !scheduler.isInStandbyMode()){
                return true;
            }
        } catch (SchedulerException e) {
            LogHelper.roomErrorLog.error("isStarted-->判断定时任务-{}是否启动异常!",jobTask,e);
        }
        return false;
    }

    private void startTaskSchedule(String jobTask){
        Scheduler scheduler = schedulerMap.get(jobTask);
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            LogHelper.roomErrorLog.error("startTaskSchedule-->启动定时任务-{}失败!",jobTask,e);
        }
    }

    private void startOnceTask(String jobTask){
        try {
            MethodInvokingJobDetailFactoryBean b = (MethodInvokingJobDetailFactoryBean)(((JobDetail)applicationContext.getBean(jobTask)).getJobDataMap().get("methodInvoker"));
            Method method =b.getPreparedMethod();
            method.invoke(b.getTargetObject(),null);
        } catch (IllegalAccessException e) {
            LogHelper.roomErrorLog.error("startOnceTask error1--> 执行任务-{}失败!",jobTask,e);
        } catch (InvocationTargetException e) {
            LogHelper.roomErrorLog.error("startOnceTask error2--> 执行任务-{}失败!",jobTask,e);
        }

    }

    private void pauseTaskSchedule(String jobTask){
        Scheduler scheduler = schedulerMap.get(jobTask);
        try {
             scheduler.standby();
        } catch (SchedulerException e) {
            LogHelper.roomErrorLog.error("stopTaskSchedule-->暂停任务-jobTask失败!",jobTask,e);
        }
    }

}
