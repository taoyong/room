package com.ty.room.service;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/5/31
 * Time: 11:00
 * Description:
 */
public interface TaskService {

    /**
     * 开启指定的时间任务
     * @param jobTask schedule作业名称
     * @param order 操作
     */
    void startWorker(String jobTask,String order);

    /**
     * 判断任务是开启
     * @param jobTask schedule作业名称
     * @return
     */
    boolean isStarted(String jobTask);
}
