package com.officerschool.onlineclassroom.service;

import com.officerschool.onlineclassroom.common.models.req.NewTaskReq;
import com.officerschool.onlineclassroom.dao.dataobject.TaskDO;
import com.officerschool.onlineclassroom.dao.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author : create by xiangwenchao@zhejianglab.com
 * @version : v1.0
 * @date : 4/16/24
 */
@Service
public class TaskService {

    @Resource
    private TaskMapper taskMapper;

    public String newTask(NewTaskReq req) {
        TaskDO taskDO = new TaskDO();
        String id = UUID.randomUUID().toString().replace("-", "");
        taskDO.setId(id);
        taskDO.setName(req.getTaskName());
        taskDO.setDescription(req.getDescription());
        taskDO.setStepNumber(req.getStepNumber());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        taskDO.setCreateTime(now);
        taskDO.setUpdateTime(now);
        taskMapper.insert(taskDO);
        return id;
    }
}
