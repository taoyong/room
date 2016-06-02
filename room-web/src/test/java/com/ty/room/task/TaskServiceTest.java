package com.ty.room.task;

import com.ty.room.domain.enumType.TaskOperateEnum;
import com.ty.room.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/6/2
 * Time: 16:25
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config-task.xml","classpath:spring-config-service.xml","classpath:spring-mvc.xml"})
public class TaskServiceTest{
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TaskService taskService;
    @Test
    public void testStartWoker(){
        String jobTask = "roomJobDetail";
        taskService.startWorker(jobTask, TaskOperateEnum.START_ONECE_TASK.order);
        log.info("testStartWoker--> over !");
    }
}
