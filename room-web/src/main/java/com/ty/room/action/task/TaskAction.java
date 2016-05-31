package com.ty.room.action.task;

import com.ty.room.domain.RoomResult;
import com.ty.room.service.TaskService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/5/31
 * Time: 10:53
 * Description:
 */
@Controller
@RequestMapping("/")
public class TaskAction {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "worker" , method = RequestMethod.GET)
    public String executorTask(@RequestParam Map<String,Object> params){
        String name = MapUtils.getString(params,"name");
        String order = MapUtils.getString(params,"order");
        taskService.startWorker(name,order);
        return "articles";
    }

    @RequestMapping(value ="isStarted", method = RequestMethod.GET)
    @ResponseBody
    public RoomResult isStarted(@RequestParam Map<String,Object> params){
        String name = MapUtils.getString(params,"name");
        boolean isStarted = taskService.isStarted(name);
        return new RoomResult(isStarted);
    }

}
